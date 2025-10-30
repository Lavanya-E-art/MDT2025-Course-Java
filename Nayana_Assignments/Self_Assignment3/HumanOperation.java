package Operations;

import java.time.Duration;

public class HumanOperation extends IOperation {
    private Duration duration;
    private int workers;
    private double hourlyRate;

    public HumanOperation(String name, Duration duration, int workers, double hourlyRate) {
        super(name);
        this.duration = duration;
        this.workers = workers;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double perform() {
        double hours = duration.toMinutes() / 60.0;
        return hours * workers * hourlyRate;
    }

    @Override
    public Duration getDuration() { return duration; }

    @Override
    public String toString() {
        return "HumanOperation{" + name + ", dur=" + duration.toMinutes() + "min, workers=" + workers + ", rate=" + hourlyRate + "}";
    }
}
