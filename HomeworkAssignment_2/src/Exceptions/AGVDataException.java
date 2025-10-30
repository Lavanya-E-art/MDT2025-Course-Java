package Exceptions;

public class AGVDataException extends Exception {
    private final String agvId;
    private final String field;

    public AGVDataException(String agvId, String field, String message, Throwable cause) {
        super("Error in AGV [" + agvId + "] field '" + field + "': " + message, cause);
        this.agvId = agvId;
        this.field = field;
    }

    public String getAgvId() { return agvId; }
    public String getField() { return field; }
}
