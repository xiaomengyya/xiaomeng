package upei.project.Card.EquipCard.Weapon;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class ZhuGeBow extends Weapon{
    public ZhuGeBow(int number){
        super("ZhuGeBow","", number);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "ZhuGeBow");
    }
}
