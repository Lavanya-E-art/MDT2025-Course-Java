import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;

/**
 * Handles all logging, archiving, and log searching operations.
 * Demonstrates usage of both byte and character streams.
 */
public class LogManager {
    private static final String LOG_DIR = "logs";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public LogManager() {
        new File(LOG_DIR).mkdirs();
    }

    public void log(String type, String name, String message) {
        try {
            String date = sdf.format(new Date());
            File dir = new File(LOG_DIR + "/" + type);
            dir.mkdirs();

            File logFile = new File(dir, name + "_" + date + ".log");

            // Character Stream - Writing log entries
            try (FileWriter fw = new FileWriter(logFile, true)) {
                fw.write("[" + new Date() + "] " + message + "\n");
            }

            // Byte Stream - Save metadata
            LogMetadata meta = new LogMetadata(logFile.getName(), type, new Date());
            writeMetadata(meta);

        } catch (IOException e) {
            System.err.println("Error writing log: " + e.getMessage());
        }
    }

    private void writeMetadata(LogMetadata meta) {
        File metaDir = new File(LOG_DIR + "/meta");
        metaDir.mkdirs();

        File metaFile = new File(metaDir, meta.getFileName() + ".meta");

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(metaFile))) {
            out.writeObject(meta);
        } catch (IOException e) {
            System.err.println("Failed to write metadata: " + e.getMessage());
        }
    }

    public void openLog(String type, String keyword) {
        File dir = new File(LOG_DIR + "/" + type);
        if (!dir.exists()) {
            System.out.println("No logs for type: " + type);
            return;
        }

        Pattern pattern = Pattern.compile(".*" + keyword + ".*");
        File[] files = dir.listFiles();
        boolean found = false;

        if (files != null) {
            for (File f : files) {
                Matcher m = pattern.matcher(f.getName());
                if (m.matches()) {
                    found = true;
                    System.out.println("\n=== Showing log: " + f.getName() + " ===");

                    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading log: " + e.getMessage());
                    }
                }
            }
        }

        if (!found) {
            System.out.println("No log found for keyword: " + keyword);
        }
    }

    public void readMetadata(String type, String keyword) {
    File metaDir = new File(LOG_DIR + "/meta");
    if (!metaDir.exists()) {
        System.out.println("No metadata found.");
        return;
    }

    Pattern pattern = Pattern.compile(".*" + keyword + ".*");
    File[] files = metaDir.listFiles();
    boolean found = false;

    if (files != null) {
        for (File f : files) {
            Matcher m = pattern.matcher(f.getName());
            if (m.matches()) {
                found = true;
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
                    LogMetadata meta = (LogMetadata) in.readObject();
                    System.out.println("\n=== Metadata for: " + f.getName() + " ===");
                    System.out.println("File Name: " + meta.getFileName());
                    System.out.println("Type: " + meta.getType());
                    System.out.println("Created On: " + meta.getCreatedOn());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to read metadata: " + e.getMessage());
                }
            }
        }
    }

    if (!found) {
        System.out.println("No metadata found for keyword: " + keyword);
    }
}

    public void archiveOldLogs() {
        File dir = new File(LOG_DIR);
        File archive = new File(LOG_DIR + "/archive");
        archive.mkdirs();

        for (File sub : Objects.requireNonNull(dir.listFiles())) {
            if (sub.isDirectory() && !sub.getName().equals("archive")) {
                for (File f : Objects.requireNonNull(sub.listFiles())) {
                    if (f.getName().endsWith(".log")) {
                        if (f.lastModified() < System.currentTimeMillis() - (2 * 24 * 60 * 60 * 1000)) { // older than 2 days
                            f.renameTo(new File(archive, f.getName()));
                        }
                    }
                }
            }
        }
    }
}
