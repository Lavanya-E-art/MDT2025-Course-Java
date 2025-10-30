package test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import Exceptions.*;
import main.*;
public class ExceptionDemo_ToobaTest {

    private ExceptionDemo_Tooba demo;
    private IndustrialProcess process;
    private AGV agv;

    @BeforeEach
    void setup() {
        demo = new ExceptionDemo_Tooba();
        agv = new AGV("AGV-T", 8.5);
        process = new IndustrialProcess();
        process.addOperation(new IOperation("Move", 5, 2));
    }

    @Test
    void testHandleMultipleExceptionsOverConsumption() {
        agv.setConsumption(12.0);
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(process, agv));
    }

    @Test
    void testRethrowExample() {
        IndustrialProcess empty = new IndustrialProcess();
        assertThrows(Exception.class, () -> demo.rethrowExample(empty));
    }

    @Test
    void testWriteSummaryToFile() {
        assertDoesNotThrow(() -> demo.writeSummaryToFile(process, "tooba_summary.txt"));
        assertTrue(new File("tooba_summary.txt").exists());
    }

    @Test
    void testAnalyzeProcessChained() {
        IndustrialProcess badProcess = new IndustrialProcess();
        badProcess.addOperation(new IOperation("Short", 2, 1));
        assertThrows(EfficiencyException.class, () -> demo.analyzeProcess(badProcess));
    }

    @Test
    void testHandleMultipleExceptionsHappyPath() {
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(process, agv));
    }
}
