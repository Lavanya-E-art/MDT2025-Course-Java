package Exceptions;

public class AGVDataException extends Exception {


    public AGVDataException(String agvId, String field, String message, Throwable cause) {
        super("Error in AGV [" + agvId + "] field '" + field + "': " + message, cause);
  
    }

  
}
