package upei.project.Card.EquipCard.Armor;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class SilverTiger extends Armor{
    public SilverTiger(int number){
        super("SilverTiger","", number);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "SilverTiger effect is triggered!");
    }
}
