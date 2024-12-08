package upei.project.Card.EquipCard.Weapon;

import upei.project.Card.EquipCard.EquipCard;
import upei.project.Controller.GameController;
import upei.project.People.Person;

public abstract class Weapon extends EquipCard {
    public Weapon(String name, String description, int number) {
        super(name, description, number);
    }

    // Weapon effects are implemented by specific subclasses
    @Override
    public abstract void activateEffect(Person self, Person opponent, GameController game);

    //public abstract void activateEffect(Person self, Person opponent, GameController game);
}
