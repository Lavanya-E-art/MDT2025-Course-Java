

public class RethrowExceptionHandler {
    private LogManager logs;

    public RethrowExceptionHandler(LogManager logs) {
        this.logs = logs;
    }

    public void processTask(Task task) throws SystemException {
        try {
            if (task == null)
                throw new NullPointerException("Task object is null!");
            if (task.getDescription() == null)
                throw new IllegalArgumentException("Task description missing!");
            if (task.getAssignedVehicleId() == null)
                throw new IllegalArgumentException("Task not assigned to any vehicle!");

            logs.log("system", "task", "Processing task: " + task);

        } catch (Exception e) {
            logs.log("system", "task", "Error processing task: " + e.getMessage());
            // rethrow wrapped
            throw new SystemException("Failed to process task: " + task, e);
        }
    }
}
