package SelfAssignment_3.Resources;
public class HardwareResource extends NonHumanResource {
    private String brand;

    public HardwareResource(String description, int nominalTime, String brand) {
        super(description, nominalTime);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String type() {
        return "Hardware Resource";
    }

    @Override
    public String toString() {
        return type() + " | " + description + " | Brand: " + brand + " | Time: " + nominalTime;
    }
}
