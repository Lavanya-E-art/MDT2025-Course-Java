package Operations;

import java.time.Duration;

public class TransportOperation extends IOperation {
    private double distanceKm;
    private double costPerKm;
    private Duration duration;

    public TransportOperation(String name, double distanceKm, double costPerKm, Duration duration) {
        super(name);
        this.distanceKm = distanceKm;
        this.costPerKm = costPerKm;
        this.duration = duration;
    }

    @Override
    public double perform() {
        return distanceKm * costPerKm;
    }

    @Override
    public Duration getDuration() { return duration; }

    @Override
    public String toString() {
        return "TransportOperation{" + name + ", dist=" + distanceKm + "km, cost/km=" + costPerKm + "}";
    }
}
