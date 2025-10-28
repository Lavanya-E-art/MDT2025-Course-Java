
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceManagementHandlerTest {

    @Test
    void testWriteToFile() throws Exception {
        ResourceManagementHandler handler = new ResourceManagementHandler();
        handler.safeWrite("testfile.txt", "Hello Resource Management");
    }

    @Test
    void testInvalidPathThrowsException() {
        ResourceManagementHandler handler = new ResourceManagementHandler();
        assertThrows(SystemException.class, () -> handler.safeWrite("/invalid/path/file.txt", "error"));
    }
}

