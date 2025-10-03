package SelfAssignment_3.Operations;
import java.util.*;

import SelfAssignment_3.Resources.Resource;

public abstract class IOperation {
    protected String id;
    protected String description;
    protected int nominalTime; // minutes
    protected List<Resource> resources;  // Generalized from AGV to Resource

    // Constructor
    public IOperation(String id, String description, int nominalTime) {
        this.id = id;
        this.description = description;
        this.nominalTime = nominalTime;
        this.resources = new ArrayList<>();
    }

    // Add any type of Resource (Human, AGV, etc.)
    public void addResource(Resource res) {
        this.resources.add(res);
    }

    public int getDuration() {
        return this.nominalTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setData(String field, Object value) {
        switch (field.toLowerCase()) {
            case "description":
                this.description = (String) value;
                break;
            case "time":
            case "nominaltime":
                this.nominalTime = (int) value;
                break;
            default:
                System.out.println("Unknown field: " + field);
        }
    }

    public String getData() {
        String details = "Operation ID: " + this.id +
                ", Desc: " + this.description +
                ", Time: " + this.nominalTime + " min" +
                ", Resources: " + this.resources.size();

        for (Resource r : resources) {
            details += "\n    -> " + r.toString();
        }

        return details;
    }

    // Energy/cost calculation should be different in child classes
    public abstract double getEnergyConsumption();

    public abstract String type();
}
