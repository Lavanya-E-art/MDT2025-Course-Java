import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private transient LogManager logs;

    public void setLogger(LogManager logs) {
        this.logs = logs;
    }

    // Add a new unassigned task
    public void addTask(String description) {
        Task task = new Task(description, null);
        tasks.add(task);
        if (logs != null) logs.log("system", "main", "Added task: " + description);
    }

    // Assign first unassigned task to the vehicle
    public void assignTask(StorageVehicle v) {
        Optional<Task> unassigned = tasks.stream()
            .filter(t -> t.getAssignedVehicleId() == null)
            .findFirst();

        if (unassigned.isPresent()) {
            Task task = unassigned.get();

            // Create new task with assigned vehicle ID and replace in list
            Task assignedTask = new Task(task.getDescription(), v.getId());
            tasks.remove(task);
            tasks.add(assignedTask);

            v.performTask(assignedTask.getDescription());
            if (logs != null) logs.log("system", "main", "Assigned task '" + assignedTask.getDescription() + "' to " + v.getId());
        } else {
            System.out.println("No unassigned tasks available.");
            if (logs != null) logs.log("system", "main", "No unassigned tasks for " + v.getId());
        }
    }

    // List all tasks with their assignment status
    public void listTasks() {
        System.out.println("Current tasks:");
        for (Task t : tasks) {
            String assigned = (t.getAssignedVehicleId() == null) ? "Unassigned" : "Assigned to " + t.getAssignedVehicleId();
            System.out.println("- " + t.getDescription() + " [" + assigned + "]");
        }
    }
}
