package main;
public class AGV {
    private String id;
    private double consumption;

    public AGV(String id, double consumption) {
        this.id = id;
        this.consumption = consumption;
    }

    public String getId() { return id; }
    public double getConsumption() { return consumption; }
    public void setConsumption(double c) { this.consumption = c; }
}