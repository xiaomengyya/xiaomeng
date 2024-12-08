package upei.project.Card.BasicCard;

import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

public abstract class BasicCard extends Card {
    public BasicCard(String name, String description, int number) {
        super(name,description,number);
    }

    public abstract void activateEffect(Person self, Person opponent, GameController game);

}
