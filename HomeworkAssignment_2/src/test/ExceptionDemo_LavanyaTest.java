package test;

import org.junit.jupiter.api.*;
import Exceptions.*;
import main.*;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class ExceptionDemo_LavanyaTest {

    private ExceptionDemo_Lavanya demo;
    private MaintenanceRecord record;
    private IndustrialProcess process;
    private AGV agv;

    @BeforeEach
    void setup() {
        demo = new ExceptionDemo_Lavanya();
        record = new MaintenanceRecord("AGV-X", "Brake calibration", 2);
        process = new IndustrialProcess();
        process.addOperation(new IOperation("Repair", 4, 3));
        agv = new AGV("AGV-X", 3.0);
    }

    @Test
    void testHandleMultipleExceptionsWithNullRecord() {
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(null, "maintenance_log.txt"));
    }

    @Test
    void testRethrowExample() {
        agv.setConsumption(-5.0);
        assertThrows(Exception.class, () -> demo.rethrowExample(agv));
    }

    @Test
    void testWriteSummaryToFile() {
        assertDoesNotThrow(() -> demo.writeSummaryToFile(process, "lavanya_report.txt"));
        assertTrue(new File("lavanya_report.txt").exists());
    }

    @Test
    void testAnalyzeProcessChained() {
        assertThrows(MaintenanceException.class, () -> demo.analyzeProcess(null));
    }

    @Test
    void testHandleMultipleExceptionsHappyPath() {
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(record, "lavanya_ok.txt"));
    }
}
