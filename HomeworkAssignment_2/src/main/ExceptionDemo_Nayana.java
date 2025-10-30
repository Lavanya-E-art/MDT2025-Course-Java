package main;
import java.io.*;
import Exceptions.*;

public class ExceptionDemo_Nayana {

    public void handleMultipleExceptions(IOperation op, Object val) {
        try {
            if (op == null) throw new NullPointerException("Operation null!");
            if (!(val instanceof Integer)) throw new ClassCastException("Invalid type!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void rethrowExample(IOperation op) throws Exception {
        try {
            if (op.getDuration() <= 0) throw new IllegalArgumentException("Bad operation time!");
        } catch (Exception e) {
            System.err.println("Rethrowing: " + e);
            throw e;
        }
    }

    public void writeSummaryToFile(IOperation op, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Operation: " + op.getName());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }

    public void analyzeProcess(IndustrialProcess process) throws SchedulingException {
        try {
            if (process.processDuration() == 0)
                throw new IllegalStateException("No scheduled operations!");
        } catch (Exception e) {
            throw new SchedulingException("Scheduling failed", e);
        }
    }
}