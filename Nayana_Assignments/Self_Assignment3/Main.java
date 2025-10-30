package Resources;

import java.time.Duration;

import Operations.HumanOperation;
import Operations.TransportOperation;
import Processes.IndustrialProcess;
import Processes.ManagementProcess;

public class Main {
    public static void main(String[] args) {
        // Create Warehouse
        Warehouse wh = new Warehouse("Main Warehouse");

        // Create an IndustrialProcess that needs material, AGV and human resources
        IndustrialProcess ip1 = new IndustrialProcess("IP-Loading", Duration.ofMinutes(120));

        // Add operations: a human operation and a transport operation
        ip1.addOperation(new HumanOperation("Pick & pack", Duration.ofMinutes(60), 2, 15.0)); // 2 workers, $15/hr
        ip1.addOperation(new TransportOperation("Move pallets", 5.0, 2.0, Duration.ofMinutes(30))); // 5 km, $2/km

        // Add resources
        ip1.addResource(new MaterialResources("Boxes", 0.5, 200)); // 200 units, $0.5 each
        ip1.addResource(new HumanResources("Pickers", 2, 15.0)); // 2 pickers
        ip1.addResource(new AGV("AGV-01", 15000.0, 1200.0, 80.0, 1.2, Duration.ofMinutes(90),
                new Position(0,0), 3.5f, 2.5f));

        wh.addProcess(ip1);

        // Create a ManagementProcess
        ManagementProcess mp = new ManagementProcess("MP-Inventory");
        mp.addOperation(new HumanOperation("Inventory audit", Duration.ofMinutes(90), 1, 20.0));
        wh.addProcess(mp);

        // Simulate once
        Warehouse.Summary summary = wh.simulateOnce();

        // Print detailed summary
        System.out.println("Warehouse: " + "Main Warehouse");
        System.out.println("Processes summary:");
        for (Warehouse.ProcessSummary ps : summary.processSummaries) {
            System.out.printf("  Process %s: operations cost = %.2f, total cost = %.2f%n",
                    ps.processId, ps.operationsCost, ps.totalCost);
        }
        System.out.printf("Totals: operations cost = %.2f, total process cost = %.2f%n",
                summary.totalOperationCost, summary.totalProcessCost);
    }
}
