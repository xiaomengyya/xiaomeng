package upei.project.Card.TacticCard;


import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

import java.util.Scanner;

//Guo He Chai Qiao

public class DestroyBridge extends TacticCard {
    public DestroyBridge(int num) {
        super("过河拆桥", "技能发动时，使对方弃置一张牌（装备或手牌）", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + " 使用了过河拆桥，目标是 " + opponent.getName());

        Scanner scanner = new Scanner(System.in);

        // 检查对方是否有牌可以弃置
        if (opponent.getHandCards().isEmpty() && opponent.getWeapon() == null && opponent.getArmor() == null) {
            System.out.println(opponent.getName() + " 没有任何牌，过河拆桥无效！");
            return;
        }

        System.out.println("请选择对方要弃置的牌类型：1-手牌，2-武器，3-防具：");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: // 弃置手牌
                if (!opponent.getHandCards().isEmpty()) {
                    Card discardedCard = opponent.getHandCards().remove(0); // 直接弃置第一张手牌
                    game.getDeck().discardCard(discardedCard);
                    System.out.println(opponent.getName() + " 弃置了手牌：" + discardedCard.getName());
                } else {
                    System.out.println(opponent.getName() + " 没有手牌，选择无效！");
                }
                break;
            case 2: // 弃置武器
                if (opponent.getWeapon() != null) {
                    Card weapon = opponent.getWeapon();
                    opponent.unequipWeapon();
                    game.getDeck().discardCard(weapon);
                    System.out.println(opponent.getName() + " 弃置了武器：" + weapon.getName());
                } else {
                    System.out.println(opponent.getName() + " 没有武器，选择无效！");
                }
                break;
            case 3: // 弃置防具
                if (opponent.getArmor() != null) {
                    Card armor = opponent.getArmor();
                    opponent.unequipArmor();
                    game.getDeck().discardCard(armor);
                    System.out.println(opponent.getName() + " 弃置了防具：" + armor.getName());
                } else {
                    System.out.println(opponent.getName() + " 没有防具，选择无效！");
                }
                break;
            default:
                System.out.println("无效选择！");
        }
    }
}
