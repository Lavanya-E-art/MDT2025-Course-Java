
public class AGV {
	private String id;
    private double batteryLoad;
    private double consumption;
    private int chargingTime;
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

    public String getData() {
        return "AGV : ID=" + id + 
        		", Battery=" + batteryLoad + 
        		", Consumption=" + consumption +
                ", ChargingTime=" + chargingTime + 
                ", Position=" + position +
                ", MaxSpeed=" + maxSpeed + 
                ", ActSpeed=" + actSpeed;
    }

    public double getConsumption() {
        return consumption;
    }
}
