import java.util.*;

public class IndustrialProcess {
	private String id;
    private List<IOperation> operations;

    public IndustrialProcess(String id) {
        this.id = id;
        this.operations = new ArrayList<>();
    }

    public void addOperation(IOperation op) {
        operations.add(op);
    }

    public int processDuration() {
    	int total = 0;
        for (IOperation op : operations) {
            total += op.getDuration();
        }
        return total;
    }

    public int processResources() {
        Set<AGV> uniqueAGVs = new HashSet<>();
        for (IOperation op : operations) {
            uniqueAGVs.addAll(op.getResources());
        }
        return uniqueAGVs.size();
    }

    public double energyConsumption() {
        double energy = 0;
        for (IOperation op : operations) {
            for (AGV agv : op.getResources()) {
                energy += agv.getConsumption() * op.getDuration(); // calculate each energy consumption
            }
        }
        return energy; // return total energy
    }

    public void printProcessInfo() {
        System.out.println("Industrial Process ID: " + id);
        for (IOperation op : operations) {
            System.out.println(op.getData());
        }
        System.out.println("Total Duration: " + processDuration() + " mins");
        System.out.println("Unique AGVs Required: " + processResources());
        System.out.println("Estimated Energy Consumption: " + energyConsumption() + " units");
    }
}
