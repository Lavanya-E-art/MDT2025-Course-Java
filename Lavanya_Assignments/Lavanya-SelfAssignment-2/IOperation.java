import java.util.List;

public class IOperation {
	private String id;
    private String description;
    private int nominalTime; // in minutes
    List<AGV> resources;

    public IOperation(String id, String description, int nominalTime, List<AGV> resources) {
        this.id = id;
        this.description = description;
        this.nominalTime = nominalTime;
        this.resources = resources;
    }

    public int getDuration() {
        return nominalTime;
    }

    public String getData() {
        return "Operation Details : ID = " + id + ", " +
        		"Desc = " + description +
                ", Time = " + nominalTime + 
                " mins, AGVs = " + resources.size();
    }

    public List<AGV> getResources() {
        return resources;
    }
}
