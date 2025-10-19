import java.io.Serializable;
import java.util.Date;

/**
 * Serializable metadata info for each log file.
 */
public class LogMetadata implements Serializable {
    private String fileName;
    private String type;
    private Date createdOn;

    public LogMetadata(String fileName, String type, Date createdOn) {
        this.fileName = fileName;
        this.type = type;
        this.createdOn = createdOn;
    }

    public String getFileName() { return fileName; }
    public String getType() { return type; }
    public Date getCreatedOn() { return createdOn; }

    @Override
    public String toString() {
        return "LogMetadata[" + fileName + ", type=" + type + ", created=" + createdOn + "]";
    }
}
