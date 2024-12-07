package upei.project.Card.StratCard;

import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

public abstract class TacticCard extends Card {
    public TacticCard(String name, String description, int number) {
        super(name, description, number);
    }

    @Override
    public abstract void activateEffect(Person self, Person opponent, GameController game);
}
