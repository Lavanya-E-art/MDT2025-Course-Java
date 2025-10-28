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
/**
 * Moves matching log files to a specified directory.
 */
/**
 * Moves matching log files to a specified directory.
 * Includes exception handling for file and I/O errors.
 */
public void moveLog(String type, String keyword, String destinationDir) {
    File srcDir = new File(LOG_DIR + "/" + type);
    File destDir = new File(destinationDir);
    destDir.mkdirs();

    Pattern pattern = Pattern.compile(".*" + keyword + ".*",Pattern.CASE_INSENSITIVE);
    boolean found = false;

    try {
        File[] files = srcDir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in directory: " + srcDir.getAbsolutePath());
            return;
        }

        for (File f : files) {
            Matcher m = pattern.matcher(f.getName());
            if (m.matches()) {
                found = true;
                File destFile = new File(destDir, f.getName());

                if (f.renameTo(destFile)) {
                    System.out.println("Moved: " + f.getName() + " -> " + destFile.getAbsolutePath());
                } else {
                    // Use File I/O Streams if rename fails (cross-disk move)
                    // input for reading
                    try (InputStream in = new FileInputStream(f);
                         OutputStream out = new FileOutputStream(destFile)) {

                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = in.read(buffer)) > 0) {
                            out.write(buffer, 0, length);
                        }
                        f.delete();
                        System.out.println("Copied and deleted original: " + f.getName());

                    } catch (IOException ioEx) {
                        System.err.println("I/O error while moving file: " + ioEx.getMessage());
                    }
                }
            }
        }
        if (!found) {
            System.out.println("No files matched keyword: " + keyword);
        }

    }  catch (Exception e) {
        System.err.println("Unexpected error during move operation: " + e.getMessage());
    }
}

/**
 * Deletes matching log files and their metadata safely.
 */
public void deleteLog(String type, String keyword) {
    File logDir = new File(LOG_DIR + "/" + type);
    File metaDir = new File(LOG_DIR + "/meta");

    Pattern pattern = Pattern.compile(".*" + keyword + ".*",Pattern.CASE_INSENSITIVE);
    boolean found = false;

    try {
        File[] logs = logDir.listFiles();
        if (logs == null || logs.length == 0) {
            System.out.println("No logs found in directory: " + logDir.getAbsolutePath());
            return;
        }
        for (File log : logs) {
            Matcher m = pattern.matcher(log.getName());
            if (m.matches()) {
                found = true;

                if (log.delete()) {
                    System.out.println("Deleted log file: " + log.getName());
                } else {
                    System.err.println("Failed to delete log: " + log.getName());
                }

                // Delete corresponding metadata file
                File metaFile = new File(metaDir, log.getName() + ".meta");
                if (metaFile.exists()) {
                    if (metaFile.delete()) {
                        System.out.println("Deleted metadata: " + metaFile.getName());
                    } else {
                        System.err.println("Failed to delete metadata: " + metaFile.getName());
                    }
                }
            }
        }
        if (!found) {
            System.out.println("No log files found for keyword: " + keyword);
        }
    }  catch (Exception e) {
        System.err.println("Unexpected error during delete operation: " + e.getMessage());
    }
}

public void archiveOldLogs() {
    File dir = new File(LOG_DIR);
    File archive = new File(LOG_DIR + "/archive");
    archive.mkdirs();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    long now = System.currentTimeMillis();
    long twoDaysMillis = 2L * 24 * 60 * 60 * 1000; // 2 days in ms

    // Get today's date at midnight (00:00)
    Date today = new Date();
    try {
        today = sdf.parse(sdf.format(today));
    } catch (Exception ignored) {}

    for (File sub : Objects.requireNonNull(dir.listFiles())) {
        if (sub.isDirectory() && !sub.getName().equals("archive")) {
            for (File f : Objects.requireNonNull(sub.listFiles())) {
                if (f.getName().endsWith(".log")) {

                    boolean shouldArchive = false;

                    // Condition 1: older than 2 days by lastModified
                    if (f.lastModified() < now - twoDaysMillis) {
                        shouldArchive = true;
                    }

                    // Condition 2: date in filename (e.g., V001_2025-10-25.log)
                    try {
                        String name = f.getName();
                        int underscoreIndex = name.indexOf('_');
                        if (underscoreIndex != -1 && name.length() >= underscoreIndex + 11) {
                            String datePart = name.substring(underscoreIndex + 1, underscoreIndex + 11);
                            Date fileDate = sdf.parse(datePart);

                            // Archive only if file date is strictly before today's date
                            if (fileDate.before(today)) {
                                shouldArchive = true;
                            }
                        }
                    } catch (Exception e) {
                        // ignore invalid or missing dates
                    }

                    if (shouldArchive) {
                        f.renameTo(new File(archive, f.getName()));
                        System.out.println("Archived: " + f.getName());
                    }
                }
            }
        }
    }
}


}
