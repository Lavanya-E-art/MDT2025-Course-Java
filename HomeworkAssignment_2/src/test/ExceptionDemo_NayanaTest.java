package test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import Exceptions.*;
import main.*;

public class ExceptionDemo_NayanaTest {

    private ExceptionDemo_Nayana demo;
    private IOperation operation;
    private IndustrialProcess process;

    @BeforeEach
    void setup() {
        demo = new ExceptionDemo_Nayana();
        operation = new IOperation("Charge", 10, 5);
        process = new IndustrialProcess();
        process.addOperation(operation);
    }

    @Test
    void testHandleMultipleExceptionsWrongType() {
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(operation, "notInteger"));
    }

    @Test
    void testRethrowExample() {
        IOperation invalidOp = new IOperation("BadOp", -2, 1);
        assertThrows(Exception.class, () -> demo.rethrowExample(invalidOp));
    }

    @Test
    void testWriteSummaryToFile() {
        assertDoesNotThrow(() -> demo.writeSummaryToFile(operation, "nayana_summary.txt"));
        assertTrue(new File("nayana_summary.txt").exists());
    }

    @Test
    void testAnalyzeProcessThrows() {
        IndustrialProcess empty = new IndustrialProcess();
        assertThrows(SchedulingException.class, () -> demo.analyzeProcess(empty));
    }

    @Test
    void testHandleMultipleExceptionsValid() {
        assertDoesNotThrow(() -> demo.handleMultipleExceptions(operation, 5));
    }
}
