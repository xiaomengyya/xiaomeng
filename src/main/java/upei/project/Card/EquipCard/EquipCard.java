package upei.project.Card.EquipCard;

import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

public abstract class EquipCard extends Card {
    public EquipCard(String name, String description, int number) {
        super(name, description, number);
    }

    @Override
    public abstract void activateEffect(Person self, Person opponent, GameController game);
}

