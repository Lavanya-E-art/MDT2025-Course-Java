package com.warehouse.simulation;

import java.util.Arrays;

public class WarehouseSimulation {
    public static void main(String[] args) {
        // Create AGVs
        AGV agv1 = new AGV("A1", 100, 3.0, 2.0, 10, 8);
        AGV agv2 = new AGV("A2", 100, 2.5, 1.5, 12, 9);
        AGV agv3 = new AGV("A3", 100, 3.5, 2.5, 11, 7);

        // Create Operations
        IOperation op1 = new IOperation("O1", "Transport goods", 2.5, Arrays.asList(agv1, agv2));
        IOperation op2 = new IOperation("O2", "Sorting", 1.5, Arrays.asList(agv2));
        IOperation op3 = new IOperation("O3", "Loading", 3.5, Arrays.asList(agv1, agv3));

        // Process P1
        IndustrialProcess process1 = new IndustrialProcess("P1", Arrays.asList(op1, op2, op3));

        // Execute Process
        process1.executeProcess();

        // Another process P2
        IOperation op4 = new IOperation("O4", "Packaging", 2.5, Arrays.asList(agv1, agv2, agv3));
        IOperation op5 = new IOperation("O5", "Dispatch", 2.0, Arrays.asList(agv2));

        IndustrialProcess process2 = new IndustrialProcess("P2", Arrays.asList(op4, op5));
        process2.executeProcess();
    }
}