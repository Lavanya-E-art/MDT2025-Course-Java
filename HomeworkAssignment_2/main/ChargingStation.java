/**
 * Represents a charging station for storage vehicles.
 */
public class ChargingStation {
    private String id;
    private boolean available = true;
    private transient LogManager logs;

    public ChargingStation(String id) {
        this.id = id;
    }

    public void setLogger(LogManager logs) { this.logs = logs; }

    public void chargeVehicle(StorageVehicle v) {
        System.out.println("Charging vehicle " + v.getId() + " at station " + id);
        available = false;
        if (logs != null) logs.log("station", id, "Started charging " + v.getId());
        available = true;
        if (logs != null) logs.log("station", id, "Charging completed for " + v.getId());
    }
}
