// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         try {
//             LogManager logs = new LogManager("logs_simple");
//             StorageVehicle v1 = new StorageVehicle("V001");
//             ChargingStation s1 = new ChargingStation("S01");
//             TaskManager tm = new TaskManager();
//             Task t1 = new Task("Move pallets from A to B", "V001");
//             tm.addTask(t1);

//             tm.assignTask(v1);
//             logs.logText("vehicle", v1.getId(), "Started task: " + t1.getDescription());
//             logs.logText("station", s1.getId(), "Station ready");
//             logs.logText("system", "main", "System running normally");

//             tm.completeTask(v1);
//             logs.logText("vehicle", v1.getId(), "Task completed successfully");

//             Scanner sc = new Scanner(System.in);
//             System.out.print("\nEnter type (vehicle/station/system): ");
//             String type = sc.nextLine();
//             System.out.print("Enter name or date keyword (e.g. V001 or 2025-10-10): ");
//             String key = sc.nextLine();

//             logs.openLog(type, key);

//             logs.showMetadata(type, key);
//             logs.archiveLog(type, key);

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LogManager logs = new LogManager();

        StorageVehicle v1 = new StorageVehicle("V001");
        StorageVehicle v2 = new StorageVehicle("V002");
        ChargingStation s1 = new ChargingStation("S01");
        ChargingStation s2 = new ChargingStation("S02");
        TaskManager tm = new TaskManager();

        // Attach same logger
        v1.setLogger(logs);
        v2.setLogger(logs);
        s1.setLogger(logs);
        s2.setLogger(logs);
        tm.setLogger(logs);

        logs.log("system", "main", "=== Storage Management System Started ===");

        // Add tasks
        tm.addTask("Move pallets from A to B");
        tm.addTask("Load goods to truck dock C");
        tm.addTask("Transport empty crates back to storage");
        tm.addTask("Inspect zone 3 shelves");

        // Assign tasks to vehicles
        tm.assignTask(v1);
        tm.assignTask(v2);

        // Vehicles perform work
        v1.performTask("Move pallets from A to B");
        v1.setLoad(85.0);
        v1.finishTask();

        v2.performTask("Load goods to truck dock C");
        v2.setLoad(45.0);
        v2.finishTask();

        // Charging stations charge vehicles
        s1.chargeVehicle(v1);
        s2.chargeVehicle(v2);

        // Another system event
        logs.log("system", "main", "System check complete. All vehicles operational.");

        // End of shift
        logs.log("system", "main", "=== End of day log ===");

        // Give user access to logs
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter type (vehicle/station/system): ");
        String type = sc.nextLine();
        System.out.print("Enter name or date keyword (e.g. V001 or 2025-10-10): ");
        String key = sc.nextLine();

        logs.openLog(type, key);
    }
}
