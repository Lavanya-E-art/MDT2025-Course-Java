package Resources;

import java.time.Duration;

public class AGV extends HardwareResources {
    private String id;
    private double batteryLoad;     // e.g. percentage 0..100
    private double consumption;     // e.g. kWh per hour
    private Duration chargingTime;  // time to charge
    private Position position;
    private float maxSpeed;
    private float actSpeed;

    public AGV(String id, double purchaseCost, double maintenanceCostPerYear,
               double batteryLoad, double consumption, Duration chargingTime,
               Position position, float maxSpeed, float actSpeed) {
        super("AGV-" + id, purchaseCost, maintenanceCostPerYear);
        this.id = id;
        this.batteryLoad = batteryLoad;
        this.consumption = consumption;
        this.chargingTime = chargingTime;
        this.position = position;
        this.maxSpeed = maxSpeed;
        this.actSpeed = actSpeed;
    }

    public String getId() { return id; }
    public double getBatteryLoad() { return batteryLoad; }
    public double getConsumption() { return consumption; }
    public Duration getChargingTime() { return chargingTime; }
    public Position getPosition() { return position; }
    public float getMaxSpeed() { return maxSpeed; }
    public float getActSpeed() { return actSpeed; }

    public void setData(double batteryLoad, double consumption, Duration chargingTime, Position pos, float actSpeed) {
        this.batteryLoad = batteryLoad;
        this.consumption = consumption;
        this.chargingTime = chargingTime;
        this.position = pos;
        this.actSpeed = actSpeed;
    }

    public String getData() {
        return String.format("AGV{id=%s, battery=%.1f%%, consumption=%.2f kWh, charge=%ds, pos=%s, maxSpeed=%.2f, actSpeed=%.2f}",
                id, batteryLoad, consumption, chargingTime.getSeconds(), position, maxSpeed, actSpeed);
    }

    @Override
    public String getAmountDescription() {
        return "AGV id " + id;
    }
}
