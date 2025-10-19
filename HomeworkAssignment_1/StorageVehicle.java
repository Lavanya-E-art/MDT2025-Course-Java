import java.io.Serializable;

/**
 * Represents a smart storage vehicle in the system.
 */
public class StorageVehicle implements Serializable {
    private String id;
    private String status;
    private double load;
    private transient LogManager logs;

    public StorageVehicle(String id) {
        this.id = id;
        this.status = "IDLE";
        this.load = 0.0;
    }

    public void setLogger(LogManager logs) { this.logs = logs; }

    public String getId() { return id; }
    public double getLoad() { return load; }

    public void performTask(String taskName) {
        System.out.println("Vehicle " + id + " performing: " + taskName);
        this.status = "WORKING";
        if (logs != null) logs.log("vehicle", id, "Started task: " + taskName);
    }

    public void finishTask() {
        this.status = "IDLE";
        System.out.println("Vehicle " + id + " finished task.");
        if (logs != null) logs.log("vehicle", id, "Finished task. Now IDLE.");
    }

    @Override
    public String toString() {
        return "Vehicle[" + id + ", status=" + status + ", load=" + load + "]";
    }
}
