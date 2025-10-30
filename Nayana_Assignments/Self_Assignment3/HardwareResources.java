package Resources;

public class HardwareResources extends NonHumanResources {
    protected double purchaseCost;
    protected double maintenanceCostPerYear;

    public HardwareResources(String name, double purchaseCost, double maintenanceCostPerYear) {
        super(name);
        this.purchaseCost = purchaseCost;
        this.maintenanceCostPerYear = maintenanceCostPerYear;
    }

    @Override
    public double getCost() {
        // default: return purchase + one-year maintenance
        return purchaseCost + maintenanceCostPerYear;
    }

    @Override
    public String getAmountDescription() {
        return "purchase: " + purchaseCost + ", maintenance/yr: " + maintenanceCostPerYear;
    }
}
