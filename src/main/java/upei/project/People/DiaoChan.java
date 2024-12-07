package upei.project.People;

import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.Controller.IO;

import java.util.ArrayList;

public class DiaoChan extends Legend {
    public DiaoChan() {
        super("DiaoChan", "Dissociation: Make the opponent discard a card with a number less than 5");
    }

    @Override
    public void activateSkill(Person self, Person opponent, GameController game) {
        ArrayList<Card> opponentHandCards = opponent.getHandCards();
        for (Card card : opponentHandCards) {
            if (card.getNumber() < 5) {
                opponentHandCards.remove(card);
                game.discardCard(card);
                IO.println(self.getName() + " uses skill，" + opponent.getName() + " discards a card " + card.getNumber());
                return;
            }
        }
        IO.println(opponent.getName() + "There are no cards to discard!");
    }
}

