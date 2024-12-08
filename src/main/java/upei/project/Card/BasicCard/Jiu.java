package upei.project.Card.BasicCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class Jiu extends BasicCard {
    public Jiu(int num) {
        super("Jiu", "Restore 1 HP or increase damage", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {


    }
}
