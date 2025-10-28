
import java.io.IOException;

public class ChainingExceptionHandler {
    public void simulateChaining() throws SystemException {
        try {
            throw new IOException("Disk not accessible");
        } catch (IOException e) {
            throw new SystemException("System initialization failed due to I/O issue", e);
        }
    }
}

