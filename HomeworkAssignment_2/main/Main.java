import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LogManager logs = new LogManager();

        StorageVehicle v1 = new StorageVehicle("V001");
        StorageVehicle v2 = new StorageVehicle("V002");
        ChargingStation s1 = new ChargingStation("S01");
        ChargingStation s2 = new ChargingStation("S02");
        TaskManager tm = new TaskManager();

        // Set logger for components
        v1.setLogger(logs);
        v2.setLogger(logs);
        s1.setLogger(logs);
        s2.setLogger(logs);
        tm.setLogger(logs);

        logs.log("system", "main", "=== Storage Management System Started ===");

        // Add tasks (unassigned)
        tm.addTask("Move pallets from A to B");
        tm.addTask("Load goods to truck dock C");
        tm.addTask("Inspect temperature sensors");

        System.out.println("\nBefore assignments:");
        tm.listTasks();

        // Assign tasks to vehicles
        tm.assignTask(v1);
        tm.assignTask(v2);

        System.out.println("\nAfter assignments:");
        tm.listTasks();

        // Simulate task performed and finished by vehicle v1
        v1.performTask("Move pallets from A to B");
        v1.finishTask();

        // Charge vehicles
        s1.chargeVehicle(v1);
        s2.chargeVehicle(v2);

        // Archive old logs
        logs.archiveOldLogs();

        logs.log("system", "main", "System check complete. All vehicles operational.");

        // ===== Exception Handling Demonstrations =====
        System.out.println("\n--- Exception Handling Demonstrations ---");
        try {
            // a. Multiple Exceptions
            MultipleExceptionHandler meh = new MultipleExceptionHandler();
            meh.simulateMultipleExceptions("invalid_date.txt"); // file may not exist, will trigger exception

        } catch (SystemException e) {
            System.err.println("MultipleExceptionHandler: " + e.getMessage());
        }

        try {
            // b. Re-throwing Exceptions
            RethrowExceptionHandler reh = new RethrowExceptionHandler(logs);
            reh.processTask(null); // deliberately passing null

        } catch (SystemException e) {
            System.err.println("RethrowExceptionHandler: " + e.getMessage());
        }

        try {
            // c. Resource Management
            ResourceManagementHandler rmh = new ResourceManagementHandler();
            rmh.safeWrite("output.txt", "Testing resource management writing");

        } catch (SystemException e) {
            System.err.println("ResourceManagementHandler: " + e.getMessage());
        }

        try {
            // d. Chaining Exceptions
            ChainingExceptionHandler ceh = new ChainingExceptionHandler();
            ceh.simulateChaining();

        } catch (SystemException e) {
            System.err.println("ChainingExceptionHandler: " + e.getMessage());
        }

        // ====== User log operations ======
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter log type to open (vehicle/station/system): ");
        String type = sc.nextLine().trim().toLowerCase();

        System.out.print("Enter log file keyword (equipment name or date e.g. V001 or 2025-10-21): ");
        String key = sc.nextLine().trim();

        logs.openLog(type, key);

        System.out.print("\nDo you want to view metadata for this log? (yes/no): ");
        String metaChoice = sc.nextLine().trim().toLowerCase();
        if (metaChoice.equals("yes")) {
            logs.readMetadata(type, key);
        }

        System.out.print("\nDo you want to move or delete a log? (move/delete/none): ");
        String action = sc.nextLine().trim().toLowerCase();

        if (action.equals("move")) {
            System.out.print("Enter destination directory path: ");
            String dest = sc.nextLine().trim();
            logs.moveLog(type, key, dest);
        } else if (action.equals("delete")) {
            logs.deleteLog(type, key);
        } else {
            System.out.println("No file operations selected.");
        }

        System.out.println("\nExiting system.");
        sc.close();
    }
}
