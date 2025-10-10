// import java.util.*;

// public class TaskManager {
//     private List<Task> tasks = new ArrayList<>();

//     public void addTask(Task t) {
//         tasks.add(t);
//         System.out.println("Added: " + t);
//     }

//     public void assignTask(StorageVehicle v) {
//         for (Task t : tasks) {
//             if (t.getAssignedVehicleId().equals(v.getId())) {
//                 v.performTask(t.getDescription());
//                 return;
//             }
//         }
//         System.out.println("No tasks found for vehicle " + v.getId());
//     }

//     public void completeTask(StorageVehicle v) {
//         v.finishTask();
//     }

//     public List<Task> getAllTasks() {
//         return tasks;
//     }
// }

import java.util.*;

public class TaskManager {
    private List<String> tasks = new ArrayList<>();
    private transient LogManager logs;

    public void setLogger(LogManager logs) {
        this.logs = logs;
    }

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
}
