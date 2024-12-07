package upei.project.People;

import upei.project.Card.Card;
import upei.project.Controller.GameController;

import java.util.ArrayList;


public class CaoCao extends Legend {
    public CaoCao() {
        super("CaoCao", "Villain: When you are hurt, you can draw a card with a number greater than 5");
    }

    @Override
    public void activateSkill(Person self, Person opponent, GameController game) {
        ArrayList<Card> deck = game.getDeck().getCards();
        for (Card card : deck) {
            if (card.getNumber() > 5) {
                System.out.println(self.getName() + "triggers the traitor skill and draw a card " + card.getNumber());
                self.drawCard(card);
                game.removeCardFromDeck(card); // Remove from the deck
                return;
            }
        }
        System.out.println("There are no cards in the deck that meet the conditions!");
    }
}
