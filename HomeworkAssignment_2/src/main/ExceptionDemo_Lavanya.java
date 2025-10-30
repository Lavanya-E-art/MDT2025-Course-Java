package main;
import java.io.*;
import Exceptions.*;
public class ExceptionDemo_Lavanya {

    public void handleMultipleExceptions(MaintenanceRecord record, String filename) {
        try {
            if (record == null) throw new NullPointerException("Maintenance record is null!");
            if (!filename.endsWith(".txt")) throw new IllegalArgumentException("Invalid file format!");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("Record: " + record.getAgvId());
            }
        } catch (IOException e) {
            System.err.println("File IO exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void rethrowExample(AGV agv) throws Exception {
        try {
            if (agv.getConsumption() < 0)
                throw new ArithmeticException("Negative consumption!");
        } catch (Exception e) {
            System.err.println("Maintenance log before rethrow: " + e);
            throw e;
        }
    }

    public void writeSummaryToFile(IndustrialProcess process, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Lavanya Summary: Duration " + process.processDuration());
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }

    public void analyzeProcess(IndustrialProcess process) throws MaintenanceException {
        try {
            if (process == null) throw new NullPointerException("Process null");
        } catch (Exception e) {
            throw new MaintenanceException("Maintenance analysis failed", e);
        }
    }
}