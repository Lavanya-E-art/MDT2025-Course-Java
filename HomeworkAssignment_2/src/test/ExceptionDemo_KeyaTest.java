package test;

import org.junit.jupiter.api.*;

import Exceptions.AGVDataException;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import main.*;
public class ExceptionDemo_KeyaTest {

    private ExceptionDemo_Keya demo;
    private AGV agv;
    private IndustrialProcess process;

    @BeforeEach
    void setup() {
        demo = new ExceptionDemo_Keya();
        agv = new AGV("AGV-1", 5.5);
        process = new IndustrialProcess();
        process.addOperation(new IOperation("Load", 10, 2));
    }

    @Test
    void testHandleMultipleExceptions() {
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(agv, "speed", 5.0));
    }

    @Test
    void testHandleMultipleExceptionsWithNullAGV() {
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(null, "speed", 5.0));
    }

    @Test
    void testRethrowExample() {
        assertThrows(Exception.class, () -> demo.rethrowExample(new IOperation("Null", 0, 0)));
    }

    @Test
    void testWriteSummaryToFile() {
        assertDoesNotThrow(() -> demo.writeSummaryToFile(process, "keya_summary.txt"));
        assertTrue(new File("keya_summary.txt").exists());
    }

    @Test
    void testAnalyzeProcessThrowsChained() {
        assertThrows(AGVDataException.class, () -> demo.analyzeProcess(null));
    }
}
