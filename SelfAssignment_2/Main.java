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


/* 
 Output
<-- Industrial Process: P001 -->
Total Duration: 75 minutes
Energy Consumption: 10.75 kWh
Operations: 
   -> Operation ID: Op1, Desc: Pick items, Time: 30 min, AGVs: 2
   	 -> AGV ID: AGV1, Battery: 80.0%, Consumption: 5.0 kWh/h, Charging Time: 60 min, Position: ZoneA, Max Speed: 10.0, Actual Speed: 8.0
   	 -> AGV ID: AGV2, Battery: 90.0%, Consumption: 4.5 kWh/h, Charging Time: 50 min, Position: ZoneB, Max Speed: 12.0, Actual Speed: 9.0
   -> Operation ID: Op2, Desc: Transport to packing, Time: 45 min, AGVs: 2
   	 -> AGV ID: AGV2, Battery: 90.0%, Consumption: 4.5 kWh/h, Charging Time: 50 min, Position: ZoneB, Max Speed: 12.0, Actual Speed: 9.0
	 -> AGV ID: AGV3, Battery: 70.0%, Consumption: 3.5 kWh/h, Charging Time: 70 min, Position: ZoneE, Max Speed: 11.0, Actual Speed: 10.0
*/