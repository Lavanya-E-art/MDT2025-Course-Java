package Processes;

import java.util.ArrayList;
import java.util.List;

import Operations.IOperation;

public abstract class Process {
    protected String id;
    protected List<IOperation> operations = new ArrayList<>();

    public Process(String id) { this.id = id; }

    public String getId() { return id; }

    public void addOperation(IOperation op) { operations.add(op); }
    public List<IOperation> getOperations() { return operations; }

    /** cost of performing all operations once (simple model) */
    public double getOperationsCost() {
        double sum = 0.0;
        for (IOperation op : operations) sum += op.perform();
        return sum;
    }

    /** override to include resources */
    public abstract double getTotalCost();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }
}
