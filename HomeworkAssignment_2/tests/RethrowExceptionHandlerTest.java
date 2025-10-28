



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RethrowExceptionHandlerTest {

    @Test
    void testNullTaskThrowsException() {
        LogManager logs = new LogManager();
        RethrowExceptionHandler handler = new RethrowExceptionHandler(logs);
        assertThrows(SystemException.class, () -> handler.processTask(null));
    }

    @Test
    void testMissingDescriptionThrowsException() {
        LogManager logs = new LogManager();
        RethrowExceptionHandler handler = new RethrowExceptionHandler(logs);
        Task t = new Task(null, "V001");
        assertThrows(SystemException.class, () -> handler.processTask(t));
    }

    @Test
    void testValidTaskNoException() throws Exception {
        LogManager logs = new LogManager();
        RethrowExceptionHandler handler = new RethrowExceptionHandler(logs);
        Task t = new Task("Deliver goods", "V001");
        handler.processTask(t);
    }
}
