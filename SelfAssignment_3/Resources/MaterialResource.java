package SelfAssignment_3.Resources;
public class MaterialResource extends NonHumanResource {
    private int quantity;

    public MaterialResource(String description, int nominalTime, int quantity) {
        super(description, nominalTime);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String type() {
        return "Material Resource";
    }

    @Override
    public String toString() {
        return type() + " | " + description + " | Quantity: " + quantity + " | Time: " + nominalTime;
    }
}
