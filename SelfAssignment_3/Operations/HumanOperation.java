package SelfAssignment_3.Operations;
public class HumanOperation extends IOperation {

    public HumanOperation(String id, String description, int nominalTime) {
        super(id, description, nominalTime);
    }

    @Override
    public double getEnergyConsumption() {
        // Humans don’t consume kWh directly → approximate or return 0
        return 0;
    }

    @Override
    public String type() {
        return "Human Operation";
    }
}
