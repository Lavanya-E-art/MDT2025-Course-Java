package Resources;

public abstract class Resources {
    protected String name;

    public Resources(String name) { this.name = name; }

    public String getName() { return name; }

    /** cost of this resource (total, depending on quantities) */
    public abstract double getCost();

    /** amount/quantity measure for informational output (optional) */
    public abstract String getAmountDescription();
}
