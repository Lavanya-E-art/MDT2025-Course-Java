import java.util.Scanner;

/**
 * Main class - ties together all system components.
 */
public class Main {
    public static void main(String[] args) {
        LogManager logs = new LogManager();

        StorageVehicle v1 = new StorageVehicle("V001");
        StorageVehicle v2 = new StorageVehicle("V002");
        ChargingStation s1 = new ChargingStation("S01");
        ChargingStation s2 = new ChargingStation("S02");
        TaskManager tm = new TaskManager();

        v1.setLogger(logs);
        v2.setLogger(logs);
        s1.setLogger(logs);
        s2.setLogger(logs);
        tm.setLogger(logs);

        logs.log("system", "main", "=== Storage Management System Started ===");

        tm.addTask("Move pallets from A to B");
        tm.addTask("Load goods to truck dock C");
        tm.addTask("Inspect temperature sensors");

        tm.assignTask(v1);
        tm.assignTask(v2);

        v1.performTask("Move pallets from A to B");
        v1.finishTask();

        s1.chargeVehicle(v1);
        s2.chargeVehicle(v2);

      
        logs.archiveOldLogs();

        logs.log("system", "main", "System check complete. All vehicles operational.");

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter type (vehicle/station/system): ");
        String type = sc.nextLine();
        System.out.print("Enter name or date keyword (e.g. V001 or 2025-10-19): ");
        String key = sc.nextLine();

        logs.openLog(type, key);
       

System.out.print("\nDo you want to view metadata for this log? (yes/no): ");
String metaChoice = sc.nextLine().trim().toLowerCase();
if (metaChoice.equals("yes")) {
    logs.readMetadata(type, key);
}

    }
}
