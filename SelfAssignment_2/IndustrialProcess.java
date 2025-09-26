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
        for (int i=0;i<operations.size();i++) {
            total += operations.get(i).getDuration();
        }
        return total;
    }

    public double processResources() {
        double energy = 0;
        for (int i=0;i<operations.size();i++) {
            energy += operations.get(i).getEnergyConsumption();
        }
        return energy;
    }

    public void printSummary() {
        System.out.println("<-- Industrial Process: " + this.id + " -->");
        System.out.println("Total Duration: " + this.processDuration() + " minutes");
        System.out.println("Energy Consumption: " + this.processResources() + " kWh");
        System.out.println("Operations: ");
        for (int i=0;i<operations.size();i++) {
            System.out.println("   -> " + operations.get(i).getData());
        }
        System.out.println();
    }
}
