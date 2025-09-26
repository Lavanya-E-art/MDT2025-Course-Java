import java.util.*;

public class IOperation {
    private String id;
    private String description;
    private int nominalTime;        // minutes
    private List<AGV> resources;

    public IOperation(String id, String description, int nominalTime) {
        this.id = id;
        this.description = description;
        this.nominalTime = nominalTime;
        this.resources = new ArrayList<>();
    }

    public void addAGV(AGV agv) {
        this.resources.add(agv);
    }

    public int getDuration() {
        return this.nominalTime;
    }

    public void setData(String description, int nominalTime) {
        this.description = description;
        this.nominalTime = nominalTime;
    }

    public String getData() {
        String details = "Operation ID: " + this.id +
                         ", Desc: " + this.description +
                         ", Time: " + this.nominalTime + " min" +
                         ", AGVs: " + this.resources.size();
        
        for (int i=0;i<resources.size();i++) {
            details += "\n   	-> " + resources.get(i).getData();
        }

        return details;
    }


    public double getEnergyConsumption() {
        double energy = 0;
        for (int i=0;i<this.resources.size();i++) {
            energy += this.resources.get(i).getConsumption() * (this.nominalTime / 60.0); // hours
        }
        return energy;
    }
}
