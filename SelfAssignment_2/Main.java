public class Main {
    public static void main(String[] args) {
        // Create AGVs
        AGV agv1 = new AGV("AGV1", 80, 5.0, 60, "ZoneA", 10f, 8f);
        AGV agv2 = new AGV("AGV2", 90, 4.5, 50, "ZoneB", 12f, 9f);
        AGV agv3 = new AGV("AGV3", 70, 3.5, 70, "ZoneE", 11f, 10f);

        // Create Operations
        IOperation op1 = new IOperation("Op1", "Pick items", 30);
        op1.addAGV(agv1);
        op1.addAGV(agv2);

        IOperation op2 = new IOperation("Op2", "Transport to packing", 45);
        op2.addAGV(agv2);
        op2.addAGV(agv3);


        // Create Process
        IndustrialProcess process1 = new IndustrialProcess("P001");
        process1.addOperation(op1);
        process1.addOperation(op2);

        // Print summary
        process1.printSummary();
    }
}
