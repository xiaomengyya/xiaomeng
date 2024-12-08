package upei.project.Card.BasicCard;
import upei.project.Controller.GameController;
import upei.project.People.Person;

import java.util.Objects;
import java.util.Set;

public class Kill extends BasicCard {
    public Kill(int number) {
        super("Kill","Deal 1 damage to the target",number);
    }

//    public Object use(Person target) {
//        if (getOwner().getWeapon().toString().equals("QingGangSword")) {
//
//        }
//
//
//        if (target.getArmor().toString().equals("Bagua")) {
//            Set<Integer> prime = Set.of(1, 2, 3, 5, 7);
//            if (prime.contains(getNumber())) {
//                return false;
//            }
//        }
//        if (target.getArmor().toString().equals("BlackShield")) {
//            Set<Integer> odd = Set.of(1, 3, 5, 7, 9);
//            if (odd.contains(getNumber())) {
//                return false;
//            }
//        }
//        if (target.getArmor().toString().equals("SilverTiger")) {
//            Set<Integer> even = Set.of(2, 4, 6, 8, 10);
//            if (even.contains(getNumber())) {
//                return false;
//            }
//        }
//        return true;
//    }

//    public boolean needChooseTarget() {
//        return true;
//    }

    public String toString() {
        return "Kill";
    }
    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {


    }

}