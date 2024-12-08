package upei.project.Card.EquipCard.Weapon;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class GuDingKnife extends Weapon{
    public GuDingKnife(int number){
        super("GuDingKnife","", number);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "GuDingKnife effect is triggered!");
    }
}
