package com.warehouse.simulation;

import java.util.List;

public class IOperation {
    private String ID;
    private String description;
    private double nominalTime;   // in hours
    private List<AGV> resources;  // AGVs assigned

    public IOperation(String ID, String description, double nominalTime, List<AGV> resources) {
        this.ID = ID;
        this.description = description;
        this.nominalTime = nominalTime;
        this.resources = resources;
    }

    // Assign new AGVs
    public void setData(List<AGV> agvs) {
        this.resources = agvs;
    }

    // Get resources
    public List<AGV> getData() {
        return resources;
    }

    // Return duration
    public double getDuration() {
        return nominalTime;
    }

    // Simulate execution
    public double execute() {
        double totalEnergy = 0;
        for (AGV agv : resources) {
            totalEnergy += agv.useEnergy(nominalTime);
        }
        System.out.println("Operation executed: ID=" + ID +
                ", Description=" + description +
                ", Time=" + nominalTime + "h" +
                ", AGVs=" + resources.size() +
                ", Energy=" + totalEnergy + " kWh");
        return totalEnergy;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return ID;
    }
}