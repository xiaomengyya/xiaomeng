package upei.project.Card.BasicCard;
import upei.project.Controller.GameController;
import upei.project.People.Person;

import java.util.Objects;
import java.util.Set;

public class Kill extends BasicCard {
    public Kill(int number) {
        super("Kill","Deal 1 damage to the target",number);
    }
    public String toString() {
        return "Kill";
    }
    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        if (opponent.getHandCards().stream().anyMatch(card -> card.getName().equals("Flash"))) {
            System.out.println(opponent.getName() + " plays a 'Flash' card to block the attack!");
            opponent.getHandCards().removeIf(card -> card.getName().equals("Flash")); // Remove one 'Flash' card
        } else {
            System.out.println(self.getName() + " uses 'Kill' on " + opponent.getName() + "!");
            opponent.takeDamage(1); // Deal 1 damage
        }
    }

}