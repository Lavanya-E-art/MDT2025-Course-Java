package Resources;

public class HumanResources extends Resources {
    private int count;
    private double hourlyRate;

    public HumanResources(String name, int count, double hourlyRate) {
        super(name);
        this.count = count;
        this.hourlyRate = hourlyRate;
    }

    public int getCount() { return count; }
    public double getHourlyRate() { return hourlyRate; }

    @Override
    public double getCost() {
        // Human resources cost depends how many hours they are used.
        // Here we return zero as baseline; actual cost added when used by operations/processes.
        return 0.0;
    }

    @Override
    public String getAmountDescription() {
        return count + " people @ " + hourlyRate + "/hr";
    }
}
