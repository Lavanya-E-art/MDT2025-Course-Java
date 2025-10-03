package SelfAssignment_3.Processes;
import SelfAssignment_3.Operations.IOperation;

public class IndustrialProcess extends Process {

    public IndustrialProcess(String id) {
        super(id);
    }

    public int processDuration() {
        int total = 0;
        for (IOperation op : operations) {
            total += op.getDuration();
        }
        return total;
    }

    public double processResources() {
        double energy = 0;
        for (IOperation op : operations) {
            energy += op.getEnergyConsumption();
        }
        return energy;
    }

    @Override
    public void printSummary() {
        System.out.println("<-- Industrial Process: " + this.id + " -->");
        System.out.println("Total Duration: " + this.processDuration() + " minutes");
        System.out.println("Energy Consumption: " + this.processResources() + " kWh");
        System.out.println("Operations: ");
        for (IOperation op : operations) {
            System.out.println("   -> " + op.getData());
        }
        System.out.println();
    }
}
