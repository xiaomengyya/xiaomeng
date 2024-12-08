package upei.project.Card.EquipCard.Armor;


import upei.project.Controller.GameController;
import upei.project.People.Person;

//Ren Wang Dun
public class RenWangShield extends Armor {
    public RenWangShield(int num) {
        super("RenWangShield", "Immune to all damage from normal kills", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + "The RenWang Shield effect is triggered, immune to normal kills!");
    }

    // 防具特殊逻辑：判断是否免疫杀的伤害
    public boolean isImmuneToKill() {
        return true; // 免疫普通杀
    }

}

