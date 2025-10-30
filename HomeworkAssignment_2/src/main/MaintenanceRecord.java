package main;
public class MaintenanceRecord {
    private String agvId;
    private String description;
    private int duration;

    public MaintenanceRecord(String agvId, String description, int duration) {
        this.agvId = agvId;
        this.description = description;
        this.duration = duration;
    }

    public String getAgvId() { return agvId; }
    public String getDescription() { return description; }
    public int getDuration() { return duration; }
}