// import java.io.*;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.regex.*;

// public class LogManager {
//     private final File rootDir;
//     private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//     public LogManager(String dirName) {
//         rootDir = new File(dirName);
//         new File(rootDir, "vehicles").mkdirs();
//         new File(rootDir, "stations").mkdirs();
//         new File(rootDir, "system").mkdirs();
//         new File(rootDir, "archive").mkdirs();
//     }

//     private String today() {
//         return dateFormat.format(new Date());
//     }

//     // --- Write log using character stream ---
//     public void logText(String type, String name, String message) {
//         try {
//             String fileName = type + "-" + name + "-" + today() + ".log";
//             File logFile = new File(rootDir, type + "s/" + fileName);

//             FileWriter fw = new FileWriter(logFile, true);
//             BufferedWriter bw = new BufferedWriter(fw);
//             bw.write("[" + new Date() + "] " + message);
//             bw.newLine();
//             bw.close();

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // --- Read logs based on regex pattern (name or date) ---
//     public void openLog(String type, String regex) {
//         File folder = new File(rootDir, type + "s");
//         Pattern pattern = Pattern.compile(regex);

//         File[] files = folder.listFiles();
//         if (files == null) return;

//         for (File f : files) {
//             if (pattern.matcher(f.getName()).find()) {
//                 System.out.println("\n--- Reading " + f.getName() + " ---");
//                 try (BufferedReader br = new BufferedReader(new FileReader(f))) {
//                     String line;
//                     while ((line = br.readLine()) != null) {
//                         System.out.println(line);
//                     }
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }

//     // --- Move (archive) logs manually ---
//     public void archiveLog(String type, String namePart) {
//         File folder = new File(rootDir, type + "s");
//         File archive = new File(rootDir, "archive");
//         archive.mkdirs();

//         File[] files = folder.listFiles();
//         if (files == null) return;

//         for (File f : files) {
//             if (f.getName().contains(namePart)) {
//                 File dest = new File(archive, f.getName());
//                 if (f.renameTo(dest)) {
//                     System.out.println("Archived: " + f.getName());
//                 }
//             }
//         }
//     }

//     // --- Delete logs by keyword ---
//     public void deleteLogs(String type, String keyword) {
//         File folder = new File(rootDir, type + "s");
//         File[] files = folder.listFiles();
//         if (files == null) return;

//         for (File f : files) {
//             if (f.getName().contains(keyword)) {
//                 if (f.delete()) {
//                     System.out.println("Deleted: " + f.getName());
//                 }
//             }
//         }
//     }

//     // --- Show simple metadata ---
//     public void showMetadata(String type, String namePart) {
//         File folder = new File(rootDir, type + "s");
//         File[] files = folder.listFiles();
//         if (files == null) return;

//         for (File f : files) {
//             if (f.getName().contains(namePart)) {
//                 System.out.println("\nFile: " + f.getName());
//                 System.out.println("  Size: " + f.length() + " bytes");
//                 System.out.println("  Last Modified: " + new Date(f.lastModified()));
//                 System.out.println("  Absolute Path: " + f.getAbsolutePath());
//             }
//         }
//     }
// }

// import java.io.*;
// import java.text.SimpleDateFormat;
// import java.util.*;

// public class LogManager {
//     private File baseDir;
//     private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//     public LogManager(String dirName) {
//         baseDir = new File(dirName);
//         if (!baseDir.exists()) baseDir.mkdirs();
//     }

//     // Create log file path: logs/type/name_YYYY-MM-DD.log
//     private File getLogFile(String type, String name) {
//         String date = dateFormat.format(new Date());
//         File typeDir = new File(baseDir, type);
//         if (!typeDir.exists()) typeDir.mkdirs();
//         return new File(typeDir, name + "_" + date + ".log");
//     }

//     // Write text entry to log file
//     public void logText(String type, String name, String message) {
//         try {
//             File logFile = getLogFile(type, name);
//             try (FileWriter fw = new FileWriter(logFile, true)) {
//                 fw.write(new Date() + " : " + message + "\n");
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Show simple metadata (size, last modified)
//     public void showMetadata(String type, String name) {
//         File logFile = getLogFile(type, name);
//         if (logFile.exists()) {
//             System.out.println("Metadata for: " + logFile.getName());
//             System.out.println("Size: " + logFile.length() + " bytes");
//             System.out.println("Last modified: " + new Date(logFile.lastModified()));
//         } else {
//             System.out.println("No log file found.");
//         }
//     }

//     // Open requested log file by name or date
//     public void openLog(String type, String keyword) {
//         File typeDir = new File(baseDir, type);
//         File[] files = typeDir.listFiles();
//         if (files == null) return;

//         for (File f : files) {
//             if (f.getName().contains(keyword)) {
//                 System.out.println("\n--- Reading log: " + f.getName() + " ---");
//                 try (BufferedReader br = new BufferedReader(new FileReader(f))) {
//                     String line;
//                     while ((line = br.readLine()) != null) {
//                         System.out.println(line);
//                     }
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }

//     // Archive log (simple rename)
//     public void archiveLog(String type, String name) {
//         File logFile = getLogFile(type, name);
//         if (logFile.exists()) {
//             File archiveDir = new File(baseDir, "archive");
//             if (!archiveDir.exists()) archiveDir.mkdirs();
//             File newFile = new File(archiveDir, logFile.getName() + ".bak");
//             logFile.renameTo(newFile);
//             System.out.println("Archived: " + newFile.getName());
//         }
//     }

//     // Delete log files containing keyword
//     public void deleteLogs(String type, String keyword) {
//         File typeDir = new File(baseDir, type);
//         File[] files = typeDir.listFiles();
//         if (files == null) return;

//         for (File f : files) {
//             if (f.getName().contains(keyword)) {
//                 f.delete();
//                 System.out.println("Deleted: " + f.getName());
//             }
//         }
//     }
// }

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogManager {
    private File baseDir = new File("logs");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public LogManager() {
        if (!baseDir.exists()) baseDir.mkdirs();
    }

    private File getLogFile(String type, String name) {
        String date = dateFormat.format(new Date());
        File typeDir = new File(baseDir, type);
        if (!typeDir.exists()) typeDir.mkdirs();
        return new File(typeDir, name + "_" + date + ".log");
    }

    public void log(String type, String name, String message) {
        try (FileWriter fw = new FileWriter(getLogFile(type, name), true)) {
            fw.write(new Date() + " : " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLog(String type, String keyword) {
        System.out.println(type+" "+keyword);
        File typeDir = new File(baseDir, type);
        File[] files = typeDir.listFiles();
        if (files == null) return;
        System.out.println("HII0");
        for (File f : files) {
            if (f.getName().contains(keyword)) {
                System.out.println("\n--- " + f.getName() + " ---");
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line;
                    while ((line = br.readLine()) != null) System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showMetadata(String type, String name) {
        File f = getLogFile(type, name);
        if (f.exists()) {
            System.out.println("Metadata for " + f.getName());
            System.out.println("Size: " + f.length() + " bytes");
            System.out.println("Last modified: " + new Date(f.lastModified()));
        } else {
            System.out.println("No log found for " + name);
        }
    }
}
