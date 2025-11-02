package HomeworkAssignment_3.simulation;



import HomeworkAssignment_3.core.*;
import java.util.concurrent.TimeUnit;

public class ChargingSimulation {
    public void run() throws InterruptedException {
        ChargingStationManager manager = new ChargingStationManager(3, 5, 15);
        AGVGenerator generator = new AGVGenerator(manager, 20, 6);
        StatisticsCollector stats = new StatisticsCollector();

        manager.processQueue();
        generator.start();

        TimeUnit.SECONDS.sleep(60); // Simulation duration

        generator.stop();
        manager.shutdown();

        stats.addServed(manager.getServed());
        stats.addAbandoned(manager.getAbandoned());
        stats.printSummary();
    }
}
