package main;
import java.util.*;

public class IndustrialProcess {
    private java.util.List<IOperation> operations = new ArrayList<>();

    public void addOperation(IOperation op) { operations.add(op); }
    public int processDuration() {
        return operations.stream().mapToInt(IOperation::getDuration).sum();
    }
    public double processResources() {
        return operations.stream().mapToDouble(IOperation::getEnergy).sum();
    }
}