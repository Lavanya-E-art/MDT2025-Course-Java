
import java.io.*;

public class ResourceManagementHandler {
    public void safeWrite(String filename, String message) throws SystemException {
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(message + "\n");
            System.out.println("Message written to file successfully.");

        } catch (IOException e) {
            throw new SystemException("Failed to write to file: " + filename, e);
        }
    }
}

