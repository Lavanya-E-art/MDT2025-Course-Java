// public class ChargingStation {
//     private String id;
//     private boolean inUse;

//     public ChargingStation(String id) {
//         this.id = id;
//         this.inUse = false;
//     }

//     public String getId() { return id; }

//     public void startCharging(StorageVehicle v) {
//         inUse = true;
//         v.setStatus("CHARGING");
//         System.out.println("Station " + id + " charging " + v.getId());
//     }

//     public void stopCharging(StorageVehicle v) {
//         inUse = false;
//         v.setStatus("IDLE");
//         System.out.println("Station " + id + " finished charging " + v.getId());
//     }

//     public boolean isInUse() { return inUse; }

//     @Override
//     public String toString() {
//         return "Station[" + id + ", inUse=" + inUse + "]";
//     }
// }

public class ChargingStation {
    private String id;
    private boolean available = true;
    private transient LogManager logs;

    public ChargingStation(String id) {
        this.id = id;
    }

    public void setLogger(LogManager logs) {
        this.logs = logs;
    }

    public void chargeVehicle(StorageVehicle v) {
        System.out.println("Charging vehicle " + v.getId() + " at station " + id);
        available = false;
        if (logs != null) logs.log("station", id, "Started charging " + v.getId());
        // simulate done
        available = true;
        if (logs != null) logs.log("station", id, "Charging completed for " + v.getId());
    }
}

