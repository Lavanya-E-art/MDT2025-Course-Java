package HomeworkAssignment_3.simulation;

import HomeworkAssignment_3.model.Station;
import HomeworkAssignment_3.model.AGV;
import java.util.*;

public class ChargingSimulation {
    private final List<Station> stations = new ArrayList<>();
    private final Queue<AGV> queue = new LinkedList<>();
    private final int totalAGVs;
    private int completedAGVs = 0;         // ✅ Add this line
    private volatile boolean running = true; // ✅ To control loop stop

    public ChargingSimulation(int numStations, int totalAGVs) {
        for (int i = 1; i <= numStations; i++) {
            stations.add(new Station(i));
        }
        this.totalAGVs = totalAGVs;
    }

    // ✅ Add this method
    public synchronized void agvFinished() {
        completedAGVs++;
        if (completedAGVs >= totalAGVs) stop();
    }

    // ✅ Add this method
    public synchronized void stop() {
        running = false;
        synchronized (queue) {
            queue.notifyAll(); // Wake up any waiting threads
        }
        System.out.println("=== All AGVs charged. Simulation stopped. ===");
    }

    // ✅ Add this getter so other threads can check if running
    public boolean isRunning() {
        return running;
    }

    public void start() {
        System.out.println("=== Starting AGV Charging Simulation ===");
        System.out.println("Stations: " + stations.size() + ", AGVs: " + totalAGVs);

        for (Station station : stations) {
            new AGVCharger(station, queue, this).start(); // Pass simulation reference
        }

        Thread generator = new Thread(new AGVGenerator(queue, totalAGVs));
        generator.start();
    }

    public static void main(String[] args) {
        ChargingSimulation sim = new ChargingSimulation(3, 10);
        sim.start();
    }
}
