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
                    Card card2 = handCards.remove(j - 1); // 因为移除第一张后索引会变化
                    opponent.drawCard(card1);
                    opponent.drawCard(card2);
                    System.out.println(self.getName() + " 使用仁德技能，将手牌 " + card1.getNumber() + " 和 " + card2.getNumber() + " 交给了 " + opponent.getName());
                    return;
                }
            }
        }
        System.out.println(self.getName() + " 没有满足条件的手牌！");
    }
}
