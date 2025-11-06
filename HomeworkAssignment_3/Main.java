package HomeworkAssignment_3;

import HomeworkAssignment_3.simulation.ChargingSimulation;

public class Main {
    public static void main(String[] args) {
        ChargingSimulation sim = new ChargingSimulation(3, 10);
        sim.start();
    }
}
