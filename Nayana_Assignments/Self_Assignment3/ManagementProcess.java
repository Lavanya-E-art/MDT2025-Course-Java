package Processes;

public class ManagementProcess extends Process {
    public ManagementProcess(String id) {
        super(id);
    }

    @Override
    public double getTotalCost() {
        // management has only operations cost here
        return getOperationsCost();
    }
}
