package HomeworkAssignment_3.simulation;

import HomeworkAssignment_3.model.AGV;
import HomeworkAssignment_3.model.Station;
import java.util.Queue;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AGVCharger extends Thread {
    private final Station station;
    private final Queue<AGV> queue;
    private final ChargingSimulation simulation;
    private final Random random = new Random();

    public AGVCharger(Station station, Queue<AGV> queue, ChargingSimulation simulation) {
        this.station = station;
        this.queue = queue;
        this.simulation = simulation;
    }

    private String now() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public void run() {
        while (simulation.isRunning()) {
            AGV agv;

            synchronized (queue) {
                while (queue.isEmpty() && simulation.isRunning()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                if (!simulation.isRunning()) return;

                agv = queue.peek();
                if (agv == null) continue;

                long waitTime = (System.currentTimeMillis() - agv.getArrivalTime()) ;
                // System.out.println(System.currentTimeMillis()+" "+waitTime+" "+agv.getArrivalTime());

                if (waitTime > 15) {
                    agv.markAbandoned();
                    queue.poll();
                    System.out.println("[" + now() + "] 🚗 AGV#" + agv.getId() +
                            " left queue after waiting " + waitTime + "s (>15s)");
                    continue;
                }

                if (station.occupy()) {
                    queue.poll();
                } else {
                    continue;
                }
            }

            try {
                agv.markStartCharging(System.currentTimeMillis());
                System.out.println("[" + now() + "] ⚡ AGV#" + agv.getId() +
                        " started charging at Station#" + station.getId());

                Thread.sleep(random.nextInt(5000) + 2000); // 2–7s charging

                agv.markEndCharging(System.currentTimeMillis());
                System.out.println("[" + now() + "] ✅ AGV#" + agv.getId() +
                        " finished charging at Station#" + station.getId() +
                        " (Duration: " +
                        ((agv.getEndChargingTime() - agv.getStartChargingTime()) / 1000) + "s)");
                simulation.agvFinished();
            } catch (InterruptedException e) {
                return;
            } finally {
                station.release();
            }
        }
    }
}
