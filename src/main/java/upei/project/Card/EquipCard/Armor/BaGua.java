package upei.project.Card.EquipCard.Armor;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class BaGua extends Armor{
    public BaGua(int number){
        super("BaGua","", number);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "BaGua effect is triggered!");
    }
}
