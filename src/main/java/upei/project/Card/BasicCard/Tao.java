package upei.project.Card.BasicCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class Tao extends BasicCard {
    public Tao(int number) {
        super("Tao", "Avoid all attacks", number);
    }

//    public Object use() {
//        getTarget().recover(1);
//        return true;
//    }

    public String toString() {
        return "Tao";
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {

    }
}