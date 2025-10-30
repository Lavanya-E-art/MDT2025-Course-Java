package main;

import Exceptions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Industrial Exception Demo (Full) ===");

        AGV agv = new AGV("AGV-1", 8.5);
        IOperation op = new IOperation("Load", 10, 5);
        IndustrialProcess process = new IndustrialProcess();
        process.addOperation(op);
        MaintenanceRecord record = new MaintenanceRecord("AGV-1", "Routine Check", 2);

        ExceptionDemo_Keya keyaDemo = new ExceptionDemo_Keya();
        ExceptionDemo_Lavanya lavDemo = new ExceptionDemo_Lavanya();
        ExceptionDemo_Nayana nayanaDemo = new ExceptionDemo_Nayana();
        ExceptionDemo_Tooba toobaDemo = new ExceptionDemo_Tooba();

        // ---------------- Keya ----------------
        System.out.println("\n[KEYA DEMO]");
        keyaDemo.handleMultipleExceptions(agv, "consumption", 9.0); // OK
        keyaDemo.handleMultipleExceptions(null, "speed", 5.0);      // triggers NullPointerException
        try {
            keyaDemo.rethrowExample(new IOperation("Null", 0, 0)); // triggers ArithmeticException
        } catch (Exception e) {
            System.err.println("Caught in main (Keya): " + e);
        }
        keyaDemo.writeSummaryToFile(process, "keya_summary.txt");
        try {
            keyaDemo.analyzeProcess(null); // triggers AGVDataException
        } catch (Exception e) {
            System.err.println("Caught (Keya analyze): " + e);
            e.getCause().printStackTrace(); 
        }

        // ---------------- Lavanya ----------------
        System.out.println("\n[LAVANYA DEMO]");
        lavDemo.handleMultipleExceptions(record, "lavanya_ok.txt"); // OK
        lavDemo.handleMultipleExceptions(null, "lavanya_bad.pdf");  // Invalid file format
        try {
            AGV badAgv = new AGV("AGV-X", -5.0);
            lavDemo.rethrowExample(badAgv); // triggers ArithmeticException
        } catch (Exception e) {
            System.err.println("Caught (Lavanya rethrow): " + e);
        }
        lavDemo.writeSummaryToFile(process, "lavanya_summary.txt");
        try {
            lavDemo.analyzeProcess(null); // triggers MaintenanceException
        } catch (Exception e) {
            System.err.println("Caught (Lavanya analyze): " + e);
        }

        // ---------------- Nayana ----------------
        System.out.println("\n[NAYANA DEMO]");
        nayanaDemo.handleMultipleExceptions(op, 5);            // OK
        nayanaDemo.handleMultipleExceptions(op, "wrongType");  // triggers ClassCastException
        try {
            nayanaDemo.rethrowExample(new IOperation("BadOp", -1, 3)); // triggers IllegalArgumentException
        } catch (Exception e) {
            System.err.println("Caught (Nayana rethrow): " + e);
        }
        nayanaDemo.writeSummaryToFile(op, "nayana_summary.txt");
        try {
            IndustrialProcess emptyProcess = new IndustrialProcess();
            nayanaDemo.analyzeProcess(emptyProcess); // triggers SchedulingException
        } catch (Exception e) {
            System.err.println("Caught (Nayana analyze): " + e);
        }

        // ---------------- Tooba ----------------
        System.out.println("\n[TOOBA DEMO]");
        toobaDemo.handleMultipleExceptions(process, agv); // OK
        AGV highConsumptionAgv = new AGV("AGV-T", 15.0);
        toobaDemo.handleMultipleExceptions(process, highConsumptionAgv); // triggers IllegalArgumentException
        try {
            IndustrialProcess shortProcess = new IndustrialProcess();
            shortProcess.addOperation(new IOperation("Short", 2, 1)); // inefficient process
            toobaDemo.analyzeProcess(shortProcess); // triggers EfficiencyException
        } catch (Exception e) {
            System.err.println("Caught (Tooba analyze): " + e);
        }
        toobaDemo.writeSummaryToFile(process, "tooba_summary.txt");

        System.out.println("\n=== Demo Complete ===");
    }
}
