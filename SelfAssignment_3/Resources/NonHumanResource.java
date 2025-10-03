package SelfAssignment_3.Resources;
public abstract class NonHumanResource extends Resource {
    public NonHumanResource(String description, int nominalTime) {
        super(description, nominalTime);
    }

    @Override
    public String type() {
        return "Non-Human Resource";
    }
}
