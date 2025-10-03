package SelfAssignment_3.Resources;
public abstract class Resource {
    protected String description;
    protected int nominalTime;

    public Resource(String description, int nominalTime) {
        this.description = description;
        this.nominalTime = nominalTime;
    }

    public String getDescription() {
        return description;
    }

    public int getNominalTime() {
        return nominalTime;
    }

    // method (type):type
    public abstract String type();
}
