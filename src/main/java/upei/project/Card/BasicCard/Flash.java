package upei.project.Card.BasicCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class Flash extends BasicCard {
    public Flash(int number) {
        super("Flash", "Negative one kill", number);
    }

    public Object use() {
        return true;
    }

    public String toString() {
        return "Flash";
    }
    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {


    }
}