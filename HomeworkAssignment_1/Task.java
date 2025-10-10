public class Task {
    private String description;
    private String assignedVehicleId;

    public Task(String description, String assignedVehicleId) {
        this.description = description;
        this.assignedVehicleId = assignedVehicleId;
    }

    public String getDescription() { return description; }
    public String getAssignedVehicleId() { return assignedVehicleId; }

    @Override
    public String toString() {
        return "Task[desc=" + description + ", vehicle=" + assignedVehicleId + "]";
    }
}
