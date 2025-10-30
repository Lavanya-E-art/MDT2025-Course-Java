package Processes;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import Resources.HumanResources;
import Resources.Resources;

public class IndustrialProcess extends Process {
    private Duration processDuration;
    private List<Resources> requiredResources = new ArrayList<>();

    public IndustrialProcess(String id, Duration processDuration) {
        super(id);
        this.processDuration = processDuration;
    }

    public Duration getProcessDuration() { return processDuration; }

    public void addResource(Resources r) { requiredResources.add(r); }
    public List<Resources> getRequiredResources() { return requiredResources; }

    @Override
    public double getTotalCost() {
        double opCost = getOperationsCost();
        double resCost = 0.0;

        for (Resources r : requiredResources) {
            // For HumanResources, estimate cost based on processDuration
            if (r instanceof HumanResources) {
                HumanResources hr = (HumanResources) r;
                double hours = processDuration.toMinutes() / 60.0;
                resCost += hours * hr.getCount() * hr.getHourlyRate();
            } else {
                resCost += r.getCost();
            }
        }
        return opCost + resCost;
    }
}
