package upei.project.People;

import upei.project.Controller.GameController;

public class Legend {
    private String name; // legend's name
    private String skillDescription;

    public Legend(String name, String skillDescription) {
        this.name = name;
        this.skillDescription = skillDescription;
    }

    public String getName() {
        return name;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    // The
    public void activateSkill(Person self, Person opponent, GameController game) {

    }
}

