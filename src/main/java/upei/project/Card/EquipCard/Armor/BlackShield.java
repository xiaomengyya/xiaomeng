package upei.project.Card.EquipCard.Armor;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class BlackShield extends Armor{
    public BlackShield(int number){
        super("BlackShield","", number);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "The Black Shield effect is triggered!");
    }
}
