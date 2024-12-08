package upei.project.Card.TacticCard;


import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

import java.util.Scanner;

//Guo He Chai Qiao

public class DestroyBridge extends TacticCard {
    public DestroyBridge(int num) {
        super("DestroyBridge", "When the skill is activated, the opponent discards a card (equipment or hand)", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + " used the bridge-breaking method, the target is " + opponent.getName());

        Scanner scanner = new Scanner(System.in);

        // 检查对方是否有牌可以弃置
        if (opponent.getHandCards().isEmpty() && opponent.getWeapon() == null && opponent.getArmor() == null) {
            System.out.println(opponent.getName() +"There is no card, crossing the river and destroying the bridge is useless!");
            return;
        }

        System.out.println("Please select the type of card that the opponent wants to discard: 1-Hand Card, 2-Weapon, 3-Armor:");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: // Discard hand card
                if (!opponent.getHandCards().isEmpty()) {
                    Card discardedCard = opponent.getHandCards().remove(0); // Discard the first card in hand directly
                    game.getDeck().discardCard(discardedCard);
                    System.out.println(opponent.getName() + " discards hand card：" + discardedCard.getName());
                } else {
                    System.out.println(opponent.getName() + "No cards in hand, the selection is invalid!");
                }
                break;
            case 2: // Discard weapon
                if (opponent.getWeapon() != null) {
                    Card weapon = opponent.getWeapon();
                    opponent.unequipWeapon();
                    game.getDeck().discardCard(weapon);
                    System.out.println(opponent.getName() + " discards weapon：" + weapon.getName());
                } else {
                    System.out.println(opponent.getName() + " Without weapons, the choice is invalid!");
                }
                break;
            case 3: // Discard armor
                if (opponent.getArmor() != null) {
                    Card armor = opponent.getArmor();
                    opponent.unequipArmor();
                    game.getDeck().discardCard(armor);
                    System.out.println(opponent.getName() + " discards armor：" + armor.getName());
                } else {
                    System.out.println(opponent.getName() + " Without armors, the choice is invalid!");
                }
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }
}
