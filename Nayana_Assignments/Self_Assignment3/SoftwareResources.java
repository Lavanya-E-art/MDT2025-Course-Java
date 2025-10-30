package Resources;

public class SoftwareResources extends NonHumanResources {
    private double licenseCost;

    public SoftwareResources(String name, double licenseCost) {
        super(name);
        this.licenseCost = licenseCost;
    }

    @Override
    public double getCost() { return licenseCost; }

    @Override
    public String getAmountDescription() {
        return "license cost: " + licenseCost;
    }
}
