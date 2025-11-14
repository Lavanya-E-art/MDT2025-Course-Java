package managers;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log file for environment-related events such as temperature alerts,
 * storage operations, and item movement. Extends SystemLog to reuse
 * the base logging functionality.
 */
public class EnvironmentLog extends SystemLog {
    
    public EnvironmentLog(String logDirectory, String timestamp) {
    	// Create a log file name using the timestamp passed in
        super(logDirectory, "environment_log_" + timestamp + ".txt");
    }
    
    @Override
    protected void initializeLogFile() {
        try {
        	// Open the log writer in append mode
            logWriter = new BufferedWriter(new FileWriter(logFilePath, true));
            // Write the header section at the top of the log file
            writeHeader();
            System.out.println("📝 Environment Log initialized: " + logFilePath);
        } catch (IOException e) {
            System.err.println("❌ Error initializing Environment log: " + e.getMessage());
        }
    }
    
    @Override
    protected void writeHeader() throws IOException {
    	// Simple header block to make log files easier to read
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
    
    /**
     * Logs temperature alerts when a zone reports a temperature
     * outside the acceptable range. Also prints the warning to stderr
     * so it stands out in the console.
     */
    
    public synchronized void logTemperatureAlert(String zoneName, double temperature) {
        String log = String.format("%s 🌡️ TEMP ALERT - %s: %.1f°C (Out of range!)",
            getTimestamp(), zoneName, temperature);
        writeToFile(log);
        System.err.println(log); // highlight alerts in console
    }
    
    /**
     * Records the movement of an item between two zones.
     */
    public synchronized void logItemTransport(String itemName, String from, String to) {
        String log = String.format("%s 📦 TRANSPORT - %s: %s → %s",
            getTimestamp(), itemName, from, to);
        writeToFile(log);
    }
    
    /**
     * Logs general storage-related events (e.g., added, removed, relocated).
     */
    
    public synchronized void logStorageEvent(String event, String details) {
        String log = String.format("%s 🏭 STORAGE - %s: %s",
            getTimestamp(), event, details);
        writeToFile(log);
    }
}





