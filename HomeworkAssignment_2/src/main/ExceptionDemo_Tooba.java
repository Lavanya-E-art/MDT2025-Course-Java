package main;
import java.io.*;
import Exceptions.*;

public class ExceptionDemo_Tooba {

    public void handleMultipleExceptions(IndustrialProcess process, AGV agv) {
        try {
            if (process == null) throw new NullPointerException("Process missing!");
            if (agv.getConsumption() > 10) throw new IllegalArgumentException("AGV over consumption!");
        } catch (Exception e) {
            System.err.println("Handle exception: " + e.getMessage());
        }
    }

    public void rethrowExample(IndustrialProcess process) throws Exception {
        try {
            if (process.processDuration() == 0)
                throw new ArithmeticException("Invalid process duration!");
        } catch (Exception e) {
            System.err.println("Rethrowing: " + e);
            throw e;
        }
    }

    public void writeSummaryToFile(IndustrialProcess process, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Tooba Process Summary: Duration " + process.processDuration());
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }

    public void analyzeProcess(IndustrialProcess process) throws EfficiencyException {
        try {
            if (process == null) throw new NullPointerException("Null process!");
            if (process.processDuration() < 5) throw new IllegalStateException("Inefficient process!");
        } catch (Exception e) {
            throw new EfficiencyException("Efficiency check failed", e);
        }
    }
}