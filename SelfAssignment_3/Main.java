package SelfAssignment_3;
import SelfAssignment_3.Resources.*;
import SelfAssignment_3.Operations.*;
import SelfAssignment_3.Processes.*;

public class Main {
    public static void main(String[] args) {

        // ------------------------------
        // Resources
        // ------------------------------
        HumanResource hr1 = new HumanResource("Worker A", 120, "Welding");
        HumanResource hr2 = new HumanResource("Supervisor", 60, "Management");
        SoftwareResource sw1 = new SoftwareResource("CAD Software", 30, "v2.5");
        MaterialResource mat1 = new MaterialResource("Steel Sheets", 10, 50);
        AGV agv1 = new AGV("AGV Model X", 0, "AGVCorp", "AGV01", 90.0, 5.0, 60, "Dock", 2.5f, 2.0f);

        // ------------------------------
        // Operations
        // ------------------------------
        HumanOperation op1 = new HumanOperation("HO01", "Welding Operation", 45);
        op1.addResource(hr1);
        op1.addResource(hr2);

        TransportOperation op2 = new TransportOperation("TO01", "Transport Steel", 30);
        op2.addResource(agv1);

        HumanOperation op3 = new HumanOperation("HO02", "Designing CAD", 60);
        op3.addResource(sw1);

        // ------------------------------
        // Processes
        // ------------------------------
        IndustrialProcess iproc = new IndustrialProcess("P001");
        iproc.addOperation(op1);
        iproc.addOperation(op2);

        ManagementProcess mproc = new ManagementProcess("M001");
        mproc.addOperation(op3);

        // ------------------------------
        // Print Summaries
        // ------------------------------
        iproc.printSummary();
        mproc.printSummary();
    }
}
/*<-- Industrial Process: P001 -->
Total Duration: 75 minutes
Energy Consumption: 2.5 kWh
Operations: 
   -> Operation ID: HO01, Desc: Welding Operation, Time: 45 min, Resources: 2
    -> Human Resource | Worker A | Skill: Welding | Time: 120
    -> Human Resource | Supervisor | Skill: Management | Time: 60
   -> Operation ID: TO01, Desc: Transport Steel, Time: 30 min, Resources: 1
    -> Autonomous Guided Vehicle (AGV) | AGV Model X | Brand: AGVCorp | AGV ID: AGV01, Battery: 90.0%, Consumption: 5.0 kWh/h, Charging Time: 60 min, Position: Dock, Max Speed: 2.5, Actual Speed: 2.0

<-- Management Process: M001 -->
Operations: 
   -> Operation ID: HO02, Desc: Designing CAD, Time: 60 min, Resources: 1
    -> Software Resource | CAD Software | Version: v2.5 | Time: 30*/

