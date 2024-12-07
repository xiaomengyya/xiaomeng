package upei.project.Card.StratCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

//Wu Gu Feng Deng
public class Harvest extends TacticCard {
    public Harvest(int num) {
        super("Harvest", "Each player draws a card from the deck", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println("A bumper harvest is activated, each player draws a card!");
        self.drawCard(game.getDeck().drawCard());
        opponent.drawCard(game.getDeck().drawCard());
    }
}

