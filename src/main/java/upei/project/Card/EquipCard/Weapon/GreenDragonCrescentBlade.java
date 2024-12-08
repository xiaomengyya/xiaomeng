package upei.project.Card.EquipCard.Weapon;


import upei.project.Controller.GameController;
import upei.project.People.Person;

public class GreenDragonCrescentBlade extends Weapon {
    public GreenDragonCrescentBlade(int num) {
        super("GreenDragonCrescentBlade", "After using a kill, you can use another kill", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "'s Green Dragon Crescent Blade effect is triggered and can be used again to kill!");
        game.allowExtraKill(self);
    }
}
