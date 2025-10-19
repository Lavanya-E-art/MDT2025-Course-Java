import java.util.*;

/**
 * Manages system-wide tasks and assigns them to vehicles.
 */
public class TaskManager {
    private List<String> tasks = new ArrayList<>();
    private transient LogManager logs;

    public void setLogger(LogManager logs) { this.logs = logs; }

    public void addTask(String task) {
        tasks.add(task);
        if (logs != null) logs.log("system", "main", "Added task: " + task);
    }

    public void assignTask(StorageVehicle v) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            if (logs != null) logs.log("system", "main", "No tasks available for " + v.getId());
        } else {
            String task = tasks.remove(0);
            v.performTask(task);
            if (logs != null) logs.log("system", "main", "Assigned " + task + " to " + v.getId());
        }
    }

    public void listTasks() {
        System.out.println("Current tasks: " + tasks);
    }
}
