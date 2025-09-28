import java.util.*;

public class Main {
	public static void main(String[] args) {
        // Create AGVs
        AGV agv1 = new AGV("AGV1", 100, 2.5, 30, "A1", 10.0f, 8.0f);
        AGV agv2 = new AGV("AGV2", 90, 3.0, 25, "B1", 9.0f, 7.5f);
        AGV agv3 = new AGV("AGV2", 70, 4.0, 40, "C1", 7.0f, 7.0f);

        // Create Operations
        List<AGV> op1AGVs = new ArrayList<>(Arrays.asList(agv1));
        List<AGV> op2AGVs = new ArrayList<>(Arrays.asList(agv1, agv2));
        List<AGV> op3AGVs = new ArrayList<>(Arrays.asList(agv1, agv2, agv3));

        IOperation op1 = new IOperation("OP1", "Load Materials", 20, op1AGVs);
        IOperation op2 = new IOperation("OP2", "Transport Materials", 40, op2AGVs);
        IOperation op3 = new IOperation("OP3", "Import Items", 30, op3AGVs);

        // Create Industrial Process
        IndustrialProcess process = new IndustrialProcess("P1");
        process.addOperation(op1);
        process.addOperation(op2);
        process.addOperation(op3);

        // Print Info
        process.printProcessInfo();
    }
}
