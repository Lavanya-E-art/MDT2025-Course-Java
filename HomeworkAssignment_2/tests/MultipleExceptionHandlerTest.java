

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultipleExceptionHandlerTest {

    @Test
    void testFileNotFoundThrowsException() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(SystemException.class, () -> handler.simulateMultipleExceptions("missing.txt"));
    }

    @Test
    void testParseErrorThrowsException() {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        assertThrows(SystemException.class, () -> handler.simulateMultipleExceptions("invalid-date.txt"));
    }

    @Test
    void testNoExceptionWhenValidFile() throws Exception {
        MultipleExceptionHandler handler = new MultipleExceptionHandler();
        // Create valid temp file
        java.nio.file.Files.writeString(java.nio.file.Path.of("valid.txt"), "2025/10/27");
        handler.simulateMultipleExceptions("valid.txt");
    }
}

