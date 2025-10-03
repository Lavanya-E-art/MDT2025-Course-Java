package SelfAssignment_3.Processes;
import java.util.*;

import SelfAssignment_3.Operations.IOperation;

// Abstract base class
public abstract class Process {
    protected String id;
    protected List<IOperation> operations;

    public Process(String id) {
        this.id = id;
        this.operations = new ArrayList<>();
    }

    public void addOperation(IOperation op) {
        operations.add(op);
    }

    // Abstract methods for subclasses to implement
    public abstract void printSummary();
}
