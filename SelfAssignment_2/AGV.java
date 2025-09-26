public class AGV {
    private String id;
    private double batteryLoad;     // in %
    private double consumption;     // energy consumed per hour
    private int chargingTime;       // in minutes
    private String position;
    private float maxSpeed;
    private float actSpeed;

    public AGV(String id, double batteryLoad, double consumption, int chargingTime,
               String position, float maxSpeed, float actSpeed) {
        this.id = id;
        this.batteryLoad = batteryLoad;
        this.consumption = consumption;
        this.chargingTime = chargingTime;
        this.position = position;
        this.maxSpeed = maxSpeed;
        this.actSpeed = actSpeed;
    }

    public void setData(String type, Object value) {
        switch (type) {
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
}
