package SelfAssignment_3.Resources;
public class HumanResource extends Resource {
    private String skill;

    public HumanResource(String description, int nominalTime, String skill) {
        super(description, nominalTime);
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    @Override
    public String type() {
        return "Human Resource";
    }

    @Override
    public String toString() {
        return type() + " | " + description + " | Skill: " + skill + " | Time: " + nominalTime;
    }
}
