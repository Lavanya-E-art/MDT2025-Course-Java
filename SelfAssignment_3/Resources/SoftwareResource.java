package SelfAssignment_3.Resources;
public class SoftwareResource extends NonHumanResource {
    private String version;

    public SoftwareResource(String description, int nominalTime, String version) {
        super(description, nominalTime);
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String type() {
        return "Software Resource";
    }

    @Override
    public String toString() {
        return type() + " | " + description + " | Version: " + version + " | Time: " + nominalTime;
    }
}
