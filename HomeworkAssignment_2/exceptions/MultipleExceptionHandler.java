
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultipleExceptionHandler {

    public void simulateMultipleExceptions(String filename) throws SystemException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String dateStr = br.readLine();

            // intentionally try to parse invalid format
            Date d = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr);

            br.close();
            System.out.println("File read successfully: " + d);

        } catch (FileNotFoundException e) {
            throw new SystemException("File not found: " + filename, e);
        } catch (IOException e) {
            throw new SystemException("I/O error while reading file: " + filename, e);
        } catch (ParseException e) {
            throw new SystemException("Failed to parse date in file: " + filename, e);
        }
    }
}
