package SelfAssignment_3.Resources;
public class AGV extends HardwareResource {
    private String id;
    private double batteryLoad;     // in %
    private double consumption;     // energy consumed per hour
    private int chargingTime;       // in minutes
    private String position;
    private float maxSpeed;
    private float actSpeed;

    public AGV(String description, int nominalTime, String brand,
               String id, double batteryLoad, double consumption,
               int chargingTime, String position, float maxSpeed, float actSpeed) {
        super(description, nominalTime, brand);
        this.id = id;
        this.batteryLoad = batteryLoad;
        this.consumption = consumption;
        this.chargingTime = chargingTime;
        this.position = position;
        this.maxSpeed = maxSpeed;
        this.actSpeed = actSpeed;
    }

    // Dynamic setter based on type
    public void setData(String type, Object value) {
        switch (type.toLowerCase()) {
            case "battery":
                this.batteryLoad = (double) value;
                break;
            case "consumption":
                this.consumption = (double) value;
                break;
            case "chargingtime":
                this.chargingTime = (int) value;
                break;
            case "position":
                this.position = (String) value;
                break;
            case "maxspeed":
                this.maxSpeed = (float) value;
                break;
            case "speed":
                this.actSpeed = (float) value;
                break;
            default:
                System.out.println("Unknown data type!");
        }
    }

    // Summary
    public String getData() {
        return "AGV ID: " + this.id +
               ", Battery: " + this.batteryLoad + "%" +
               ", Consumption: " + this.consumption + " kWh/h" +
               ", Charging Time: " + this.chargingTime + " min" +
               ", Position: " + this.position +
               ", Max Speed: " + this.maxSpeed +
               ", Actual Speed: " + this.actSpeed;
    }

    public double getConsumption() {
        return consumption;
    }

    @Override
    public String type() {
        return "Autonomous Guided Vehicle (AGV)";
    }

    @Override
    public String toString() {
        return type() + " | " + getDescription() + " | Brand: " + getBrand() +
               " | " + getData();
    }
}
