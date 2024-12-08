package upei.project.Card.TacticCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

//Wu Xie Ke Ji
public class Nullify extends TacticCard {
    public Nullify(int num) {
        super("Nullify", "Used to cancel the effect of your own trick (such as \"stealing\")", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "Use Invulnerable to cancel the current trick card effect!");
    }
}

