package SelfAssignment_3.Operations;

import SelfAssignment_3.Resources.AGV;
import SelfAssignment_3.Resources.Resource;

public class TransportOperation extends IOperation {

    public TransportOperation(String id, String description, int nominalTime) {
        super(id, description, nominalTime);
    }

    @Override
    public double getEnergyConsumption() {
        double energy = 0;
        for (Resource r : resources) {
            if (r instanceof AGV) {
                AGV agv = (AGV) r;
                energy += agv.getConsumption() * (nominalTime / 60.0); // kWh
            }
        }
        return energy;
    }

    @Override
    public String type() {
        return "Transport Operation";
    }
}
