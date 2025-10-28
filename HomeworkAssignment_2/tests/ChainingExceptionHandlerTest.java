
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChainingExceptionHandlerTest {

    @Test
    void testChainedExceptionThrown() {
        ChainingExceptionHandler handler = new ChainingExceptionHandler();
        SystemException e = assertThrows(SystemException.class, handler::simulateChaining);
        assertNotNull(e.getCause());
        assertTrue(e.getCause() instanceof java.io.IOException);
    }
}

