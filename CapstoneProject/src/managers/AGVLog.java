package managers;

import java.io.IOException;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class AGVLog extends SystemLog {
    
    public AGVLog(String logDirectory, String timestamp) {
        super(logDirectory, "agv_log_" + timestamp + ".txt");
    }
    
    @Override
    protected void initializeLogFile() {
        try {
            logWriter = new BufferedWriter(new FileWriter(logFilePath, true));
            writeHeader();
            System.out.println("📝 AGV Log initialized: " + logFilePath);
        } catch (IOException e) {
            System.err.println("❌ Error initializing AGV log: " + e.getMessage());
        }
    }
    
    @Override
    protected void writeHeader() throws IOException {
        logWriter.write("=".repeat(60));
        logWriter.newLine();
        logWriter.write("  AGV LOG");
        logWriter.newLine();
        logWriter.write("  Session started: " + dateFormat.format(new Date()));
        logWriter.newLine();
        logWriter.write("=".repeat(60));
        logWriter.newLine();
        logWriter.newLine();
        logWriter.flush();
    }
    
    public synchronized void logMovement(String agvId, String from, String to) {
        String log = String.format("%s 🚗 MOVEMENT - AGV-%s: %s → %s",
            getTimestamp(), agvId, from, to);
        writeToFile(log);
    }
    
    public synchronized void logBattery(String agvId, double batteryLevel, String action) {
        String log = String.format("%s 🔋 BATTERY - AGV-%s: %.1f%% - %s",
            getTimestamp(), agvId, batteryLevel, action);
        writeToFile(log);
        System.out.println(log);
    }
    
    public synchronized void logStatusChange(String agvId, String oldStatus, String newStatus) {
        String log = String.format("%s 📊 STATUS - AGV-%s: %s → %s",
            getTimestamp(), agvId, oldStatus, newStatus);
        writeToFile(log);
    }
    
    public synchronized void logChargingStart(String agvId, String stationId, double batteryLevel) {
        String log = String.format("%s ⚡ CHARGING START - AGV-%s at %s (Battery: %.1f%%)",
            getTimestamp(), agvId, stationId, batteryLevel);
        writeToFile(log);
        System.out.println(log);
    }
    
    public synchronized void logChargingComplete(String agvId, double initialBattery) {
        String log = String.format("%s ✅ CHARGING COMPLETE - AGV-%s (%.1f%% → 100%%)",
            getTimestamp(), agvId, initialBattery);
        writeToFile(log);
        System.out.println(log);
    }
}
