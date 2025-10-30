package main;
public class IOperation {
    private String name;
    private int duration;
    private int energy;

    public IOperation(String name, int duration, int energy) {
        this.name = name;
        this.duration = duration;
        this.energy = energy;
    }

    public int getDuration() { return duration; }
    public int getEnergy() { return energy; }
    public String getName() { return name; }
}