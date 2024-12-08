package upei.project.Card.EquipCard.Weapon;

import upei.project.Controller.GameController;
import upei.project.People.Person;

public class QingGangSword extends Weapon{
    public QingGangSword(int number){
        super("QingGangSword","", number);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "QingGangSword effect is triggered!");
    }
}
