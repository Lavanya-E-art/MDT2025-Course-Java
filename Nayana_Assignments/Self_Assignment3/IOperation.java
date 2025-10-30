package Operations;

import java.time.Duration;

public abstract class IOperation {
    protected String name;

    public IOperation(String name) { this.name = name; }

    public String getName() { return name; }

    /** Execute the operation and return the total cost for performing it once. */
    public abstract double perform();

    /** duration of this operation when performed once (for reporting) */
    public abstract Duration getDuration();
}
