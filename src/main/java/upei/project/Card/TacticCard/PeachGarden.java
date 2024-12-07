package upei.project.Card.TacticCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

//Tao Yuan Jie Yi
public class PeachGarden extends TacticCard {
    public PeachGarden(int num) {
        super("PeachGarden", "Everyone recovers 1 health point", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println("The Peach Garden Oath is activated, all players recover 1 health point!");
        self.heal(1);
        opponent.heal(1);
    }
}

