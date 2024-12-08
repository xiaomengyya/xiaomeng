package upei.project.People;

import upei.project.Card.Card;
import upei.project.Controller.GameController;

import java.util.ArrayList;

public class LiuBei extends Legend {
    public LiuBei() {
        super("LiuBei", "Ren De: You can give two cards with an odd sum to the opponent.");
    }

    @Override
    public void activateSkill(Person self, Person opponent, GameController game) {
        ArrayList<Card> handCards = self.getHandCards();
        for (int i = 0; i < handCards.size(); i++) {
            for (int j = i + 1; j < handCards.size(); j++) {
                if ((handCards.get(i).getNumber() + handCards.get(j).getNumber()) % 2 == 1) {
                    Card card1 = handCards.remove(i);
                    Card card2 = handCards.remove(j - 1); // Because the index will change after removing the first one
                    opponent.drawCard(card1);
                    opponent.drawCard(card2);
                    System.out.println(self.getName() + "uses the Ren De skill to" + card1.getNumber() + " and " + card2.getNumber() + " handed over " + opponent.getName());
                    return;
                }
            }
        }
        System.out.println(self.getName() + "There are no matching hands!");
    }
}
