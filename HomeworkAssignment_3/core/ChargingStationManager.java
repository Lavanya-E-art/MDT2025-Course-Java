package HomeworkAssignment_3.core;



import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;

import HomeworkAssignment_3.model.*;



public class ChargingStationManager {
    private final List<Station> stations;
    private final Semaphore availableStations;
    private final BlockingQueue<AGV> waitingQueue;
    private final ExecutorService executor;
    private final AtomicInteger served = new AtomicInteger(0);
    private final AtomicInteger abandoned = new AtomicInteger(0);
    private final int chargeDuration;
    private final int maxWaitMinutes;

    public ChargingStationManager(int stationCount, int chargeDuration, int maxWaitMinutes) {
        this.stations = new ArrayList<>();
        for (int i = 1; i <= stationCount; i++) stations.add(new Station(i));
        this.availableStations = new Semaphore(stationCount);
        this.waitingQueue = new LinkedBlockingQueue<>();
        this.executor = Executors.newCachedThreadPool();
        this.chargeDuration = chargeDuration;
        this.maxWaitMinutes = maxWaitMinutes;
    }

    public void handleArrival(AGV agv) {
        executor.submit(() -> {
            boolean acquired = availableStations.tryAcquire();
            if (acquired) {
                startCharging(agv);
            } else {
                agv.markQueueEnter(currentMinute());
                waitingQueue.offer(agv);
                log(agv + " queued at " + agv.getArrivalTime() + " min");
            }
        });
    }

    private void startCharging(AGV agv) {
        agv.markStartCharging(currentMinute());
        log(agv + " started charging.");
        executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(chargeDuration);
                agv.markEndCharging(currentMinute());
                log(agv + " finished charging.");
                served.incrementAndGet();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                availableStations.release();
            }
        });
    }

    public void processQueue() {
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    AGV next = waitingQueue.poll(1, TimeUnit.SECONDS);
                    if (next == null) continue;
                    long waited = currentMinute() - next.getArrivalTime();
                    if (waited > maxWaitMinutes) {
                        next.markAbandoned();
                        abandoned.incrementAndGet();
                        log(next + " left after waiting " + waited + " min.");
                        continue;
                    }
                    availableStations.acquire();
                    startCharging(next);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void shutdown() {
        executor.shutdownNow();
    }

    public int getServed() { return served.get(); }
    public int getAbandoned() { return abandoned.get(); }

    private static void log(String msg) {
        System.out.printf("[%tT] %s%n", new Date(), msg);
    }

    private static long currentMinute() {
        return (System.currentTimeMillis() / 1000);
    }
}

