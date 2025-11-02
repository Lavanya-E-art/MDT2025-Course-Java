package HomeworkAssignment_3.core;



import HomeworkAssignment_3.model.AGV;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

public class AGVGenerator {
    private final ChargingStationManager manager;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Random random = new Random();
    private final int totalAGVs;
    private final int maxArrivalInterval;

    public AGVGenerator(ChargingStationManager manager, int totalAGVs, int maxArrivalInterval) {
        this.manager = manager;
        this.totalAGVs = totalAGVs;
        this.maxArrivalInterval = maxArrivalInterval;
    }

    public void start() {
        for (int i = 1; i <= totalAGVs; i++) {
            int delay = random.nextInt(maxArrivalInterval + 1);
            int id = i;
            scheduler.schedule(() -> {
                AGV agv = new AGV(id, currentMinute());
                log("AGV#" + id + " arrived.");
                manager.handleArrival(agv);
            }, delay, TimeUnit.SECONDS);
        }
    }

    public void stop() {
        scheduler.shutdownNow();
    }

    private static void log(String msg) {
        System.out.printf("[%tT] %s%n", new Date(), msg);
    }

    private static long currentMinute() {
        return (System.currentTimeMillis() / 1000);
    }
}

