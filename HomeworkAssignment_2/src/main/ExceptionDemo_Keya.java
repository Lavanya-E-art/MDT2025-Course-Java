package main;
import Exceptions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ExceptionDemo_Keya {

    public void handleMultipleExceptions(AGV agv, String type, Object value) {
        try {
            if (agv == null) throw new NullPointerException("AGV is null!");
            if (!(value instanceof Double)) throw new ClassCastException("Invalid type!");
            agv.setConsumption((double) value);
        } catch (NullPointerException | ClassCastException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown exception: " + e.getMessage());
        }
    }

    public void rethrowExample(IOperation op) throws Exception {
        try {
            if (op.getDuration() <= 0)
                throw new ArithmeticException("Invalid duration!");
        } catch (Exception e) {
            System.err.println("Logging before rethrow: " + e);
            throw e;
        }
    }

    public void writeSummaryToFile(IndustrialProcess process, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Process Duration: " + process.processDuration() + "\n");
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }

    public void analyzeProcess(IndustrialProcess process) throws AGVDataException {
        try {
            if (process == null) throw new NullPointerException("Process null");
            if (process.processDuration() == 0) throw new IllegalStateException("Empty process");
        } catch (Exception e) {
            throw new AGVDataException("AGV-UNKNOWN", "process", "Failed analysis", e);
        }
    }

}