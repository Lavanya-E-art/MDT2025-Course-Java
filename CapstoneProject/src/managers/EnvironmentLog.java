package managers;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class EnvironmentLog extends SystemLog {
    
    public EnvironmentLog(String logDirectory, String timestamp) {
        super(logDirectory, "environment_log_" + timestamp + ".txt");
    }
    
    @Override
    protected void initializeLogFile() {
        try {
            logWriter = new BufferedWriter(new FileWriter(logFilePath, true));
            writeHeader();
            System.out.println("📝 Environment Log initialized: " + logFilePath);
        } catch (IOException e) {
            System.err.println("❌ Error initializing Environment log: " + e.getMessage());
        }
    }
    
    @Override
    protected void writeHeader() throws IOException {
        logWriter.write("=".repeat(60));
        logWriter.newLine();
        logWriter.write("  ENVIRONMENT LOG");
        logWriter.newLine();
        logWriter.write("  Session started: " + dateFormat.format(new Date()));
        logWriter.newLine();
        logWriter.write("=".repeat(60));
        logWriter.newLine();
        logWriter.newLine();
        logWriter.flush();
    }
    
    public synchronized void logTemperatureAlert(String zoneName, double temperature) {
        String log = String.format("%s 🌡️ TEMP ALERT - %s: %.1f°C (Out of range!)",
            getTimestamp(), zoneName, temperature);
        writeToFile(log);
        System.err.println(log);
    }
    
    public synchronized void logItemTransport(String itemName, String from, String to) {
        String log = String.format("%s 📦 TRANSPORT - %s: %s → %s",
            getTimestamp(), itemName, from, to);
        writeToFile(log);
    }
    
    public synchronized void logStorageEvent(String event, String details) {
        String log = String.format("%s 🏭 STORAGE - %s: %s",
            getTimestamp(), event, details);
        writeToFile(log);
    }
}