
package com.warehouse.simulation;

public class AGV {
    private String ID;
    private double batteryLoad;     // %
    private double consumption;     // kWh per hour
    private double chargingTime;    // hours to full charge
    private double maxSpeed;        // max speed
    private double actSpeed;        // actual speed
    private int[] position;         // (x,y) position in warehouse

    public AGV(String ID, double batteryLoad, double consumption, double chargingTime, double maxSpeed, double actSpeed) {
        this.ID = ID;
        this.batteryLoad = batteryLoad;
        this.consumption = consumption;
        this.chargingTime = chargingTime;
        this.maxSpeed = maxSpeed;
        this.actSpeed = actSpeed;
        this.position = new int[]{0, 0}; // default position
    }

    // Simulate using energy for some duration
    public double useEnergy(double duration) {
        double energyUsed = consumption * duration;
        batteryLoad -= energyUsed;
        if (batteryLoad < 0) batteryLoad = 0;
        return energyUsed;
    }

    // Simulate charging back to 100%
    public void charge() {
        batteryLoad = 100;
        System.out.println("AGV " + ID + " charged in " + chargingTime + " hours.");
    }

    // Getter and Setter for position
    public void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
    }

    public int[] getPosition() {
        return position;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "AGV{" +
                "ID='" + ID + '\'' +
                ", batteryLoad=" + batteryLoad +
                ", consumption=" + consumption +
                ", chargingTime=" + chargingTime +
                ", maxSpeed=" + maxSpeed +
                ", actSpeed=" + actSpeed +
                '}';
    }
}
