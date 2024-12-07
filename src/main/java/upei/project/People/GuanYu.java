package upei.project.People;

import upei.project.Card.BasicCard.Kill;
import upei.project.Card.Card;
import upei.project.Controller.GameController;

import java.util.ArrayList;

public class GuanYu extends Legend {
    public GuanYu() {
        super("GuanYu", "Wusheng: You can use cards with even numbers as \"kill\"");
    }

    @Override
    public void activateSkill(Person self, Person opponent, GameController game) {
        ArrayList<Card> handCards = self.getHandCards();
        boolean foundEvenCard = false;

        for (Card card : handCards) {
            if (card.getNumber() % 2 == 0) { // 判断偶数手牌
                System.out.println(self.getName() + " uses the Wusheng skill to " + card.getName() + " (number：" + card.getNumber() + ") 当作“杀”使用！");
                Kill kill = new Kill(card.getNumber());
                game.playCard(kill, self, opponent); // 执行攻击逻辑
                self.getHandCards().remove(card); // 从手牌中移除该卡
                foundEvenCard = true;
                break; // 技能只使用一次
            }
        }

        if (!foundEvenCard) {
            System.out.println(self.getName() + "There are no even-numbered cards that meet the requirements to activate the Wusheng skill!");
        }
    }
}
