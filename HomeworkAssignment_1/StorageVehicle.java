import java.io.Serializable;

public class StorageVehicle implements Serializable {
    private String id;
    private String status;
    private double load;
    private transient LogManager logs; // transient to skip serialization

    public StorageVehicle(String id) {
        this.id = id;
        this.status = "IDLE";
        this.load = 0.0;
    }

    // Setter for logger (so Main can inject one shared logger)
    public void setLogger(LogManager logs) {
        this.logs = logs;
    }

    public String getId() { return id; }
    public String getStatus() { return status; }
    public double getLoad() { return load; }

    public void setStatus(String status) { this.status = status; }
    public void setLoad(double load) { this.load = load; }

    public void performTask(String taskName) {
        System.out.println("Vehicle " + id + " performing task: " + taskName);
        this.status = "WORKING";
        if (logs != null) logs.log("vehicle", id, "Started task: " + taskName);
    }

    public void finishTask() {
        this.status = "IDLE";
        System.out.println("Vehicle " + id + " finished task.");
        if (logs != null) logs.log("vehicle", id, "Finished task, now IDLE.");
    }

    @Override
    public String toString() {
        return "Vehicle[" + id + ", status=" + status + ", load=" + load + "]";
    }
}
