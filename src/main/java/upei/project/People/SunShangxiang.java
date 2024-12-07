package upei.project.People;

import upei.project.Card.Card;
import upei.project.Controller.GameController;

import java.util.ArrayList;

public class SunShangxiang extends Legend {
    public SunShangxiang() {
        super("SunShangxiang", "Xiao Ji: When you lose your equipment card, you can draw two cards with odd numbers.");
    }

    @Override
    public void activateSkill(Person self, Person opponent, GameController game) {
        ArrayList<Card> deck = game.getDeck().getCards();
        int count = 0;
        for (Card card : deck) {
            if (card.getNumber() % 2 == 1) { // 奇数牌
                System.out.println(self.getName() + "Trigger the skill of Xiao Ji and draw a card" + card.getNumber());
                self.drawCard(card);
                game.removeCardFromDeck(card);
                count++;
                if (count == 2) break;
            }
        }
        if (count < 2) {
            System.out.println("There aren't enough odd cards in the deck!");
        }
    }
}

