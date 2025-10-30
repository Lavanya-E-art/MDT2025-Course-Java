package Resources;

public class MaterialResources extends NonHumanResources {
    private double unitCost;
    private int quantity;

    public MaterialResources(String name, double unitCost, int quantity) {
        super(name);
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public double getUnitCost() { return unitCost; }
    public int getQuantity() { return quantity; }

    @Override
    public double getCost() {
        return unitCost * quantity;
    }

    @Override
    public String getAmountDescription() {
        return quantity + " units @ " + unitCost + " each";
    }
}
