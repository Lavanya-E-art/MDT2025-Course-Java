package SelfAssignment_3.Processes;
import SelfAssignment_3.Operations.IOperation;

public class ManagementProcess extends Process {

    public ManagementProcess(String id) {
        super(id);
    }

    @Override
    public void printSummary() {
        System.out.println("<-- Management Process: " + this.id + " -->");
        System.out.println("Operations: ");
        for (IOperation op : operations) {
            System.out.println("   -> " + op.getData());
        }
        System.out.println();
    }
}
