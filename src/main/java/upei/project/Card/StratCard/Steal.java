package upei.project.Card.StratCard;

import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

//Shun Shou Qian Yang
public class Steal extends TacticCard {
    public Steal(int num) {
        super("Steal", "Draw a card from your opponent's hand", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        if (opponent.getHandCards().isEmpty()) {
            System.out.println(opponent.getName() + "Without a card in hand, stealing is useless!");
        } else {
            Card stolenCard = opponent.getHandCards().remove(0);
            self.getHandCards().add(stolenCard);
            System.out.println(self.getName() + " uses stealing, draws card " + stolenCard.getName() + "  from " + opponent.getName());
        }
    }
}

