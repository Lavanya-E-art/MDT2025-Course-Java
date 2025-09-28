package com.warehouse.simulation;

class Result {
    private double duration;
    private int agvCount;
    private double energy;

    public Result(double duration, int agvCount, double energy) {
        this.duration = duration;
        this.agvCount = agvCount;
        this.energy = energy;
    }

    public double getDuration() { return duration; }
    public int getAgvCount() { return agvCount; }
    public double getEnergy() { return energy; }
}
