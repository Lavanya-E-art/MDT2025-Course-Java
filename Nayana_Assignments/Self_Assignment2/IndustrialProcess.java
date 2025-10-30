package com.warehouse.simulation;

import java.util.List;

public class IndustrialProcess {
    private String ID;
    private List<IOperation> operations;

    public IndustrialProcess(String ID, List<IOperation> operations) {
        this.ID = ID;
        this.operations = operations;
    }

    // Calculate total duration
    public double ProcessDuration() {
        double total = 0;
        for (IOperation op : operations) {
            total += op.getDuration();  // sequential execution
        }
        return total;
    }

    // Count total AGVs
    public int ProcessResources() {
        return (int) operations.stream()
                .flatMap(op -> op.getData().stream())
                .map(AGV::getID)
                .distinct()
                .count();
    }

    // Run the process
    public void executeProcess() {
        System.out.println("\nExecuting Industrial Process: " + ID);
        double totalEnergy = 0;
        for (IOperation op : operations) {
            totalEnergy += op.execute();
        }

        System.out.println("=== Process Summary ===");
        System.out.println("Process " + ID + " completed in " + ProcessDuration() + " hours");
        System.out.println("AGVs used: " + ProcessResources());
        System.out.println("Total Energy Consumption: " + totalEnergy + " kWh");
    }
}