package upei.project.People;
import upei.project.Card.EquipCard.Armor.Armor;
import upei.project.Card.EquipCard.Weapon.Weapon;
import upei.project.Controller.GameController;
//import upei.project.Controller.IO;
import upei.project.Card.Card;
import upei.project.Card.EquipCard.EquipCard;

import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    private final String name;
    private int maxHP;
    private int curHP;

    private Armor armor;
    private Weapon weapon;
    private Legend legend;

    private ArrayList<Card> handCards;

    private boolean hasUsedKill;
    private boolean extraKillAllowed; // 是否允许额外使用“杀”

    private int maxShaCount = 1;
    private int shaCount = maxShaCount;


    public Person(String name, int maxHP){
        this.name = name;
        this.curHP = maxHP;
        this.maxHP = maxHP;
        this.handCards = new ArrayList<>();
        this.weapon = null;
        this.armor = null;
        this.legend = null;
        this.hasUsedKill = false;
        this.extraKillAllowed = false; // 初始状态下不允许额外使用“杀”
    }

    // reset round
    public void resetTurn() {
        hasUsedKill = false;
    }

    //Draw Card
    public void drawCard(Card card) {
        handCards.add(card);
    }

    // 出牌
//    public boolean useCard(Card card, Person opponent, GameController game) {
//        if (card.getName().equals("Kill")) {
//            if (hasUsedKill) {
//                System.out.println(name + "\"Kill\" has been used in this round and cannot be used again!");
//                return false;
//            }
//            hasUsedKill = true;
//        }
//        handCards.remove(card);
//        game.playCard(card, this, opponent);
//        return true;
//    }


    //Has Card
    public boolean hasCard(String cardName) {
        return handCards.stream().anyMatch(c -> c.getName().equals(cardName));
    }



    public void assignLegend(Legend legend) {
        this.legend = legend;
        System.out.println(name + " 分配的 Legend 是: " + legend);
    }

    // Equip Armor / Weapon(Play can only equip one armor and one weapon)
    public void equip(EquipCard equipCard) {
        if (equipCard instanceof Weapon) {
            if (weapon != null) {
                System.out.println(name + " 当前已经装备了武器：" + weapon.getName());
                System.out.println("是否卸下当前武器并装备新的？输入 0 表示是，输入 1 表示否：");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if (choice == 0) {
                    handCards.add(weapon);
                    System.out.println(name + " 卸下了武器：" + weapon.getName());
                    weapon = (Weapon) equipCard;
                    System.out.println(name + " 装备了武器：" + equipCard.getName());
                } else {
                    System.out.println(name + " 保留了当前武器，未装备新武器！");
                }
            } else {
                weapon = (Weapon) equipCard;
                System.out.println(name + " 装备了武器：" + equipCard.getName());
            }
        } else if (equipCard instanceof Armor) {
            if (armor != null) {
                System.out.println(name + " 当前已经装备了防具：" + armor.getName());
                System.out.println("是否卸下当前防具并装备新的？输入 0 表示是，输入 1 表示否：");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if (choice == 0) {
                    handCards.add(armor);
                    System.out.println(name + " 卸下了防具：" + armor.getName());
                    armor = (Armor) equipCard;
                    System.out.println(name + " 装备了防具：" + equipCard.getName());
                } else {
                    System.out.println(name + " 保留了当前防具，未装备新防具！");
                }
            } else {
                armor = (Armor) equipCard;
                System.out.println(name + " 装备了防具：" + equipCard.getName());
            }
        }
    }

    //Unequip Weapon
    public void unequipWeapon() {
        if (weapon != null) {
            System.out.println(name + " 卸下了武器：" + weapon.getName());
            weapon = null;
        } else {
            System.out.println(name + " 没有武器可卸下！");
        }
    }
    //Unequip Armor
    public void unequipArmor() {
        if (armor != null) {
            System.out.println(name + " 卸下了防具：" + armor.getName());
            armor = null;
        } else {
            System.out.println(name + " 没有防具可卸下！");
        }
    }


    // Take damage
    public void takeDamage(int damage) {
        if (armor != null && armor.getName().equals("仁王盾")) {
            System.out.println(name + " 的仁王盾触发，免疫此次伤害！");
            return;
        }
        curHP -= damage;
        System.out.println(name + " 受到 " + damage + " 点伤害，剩余血量：" + curHP);
        if (curHP <= 0) {
            System.out.println(name + " 已经阵亡！");
        }
    }

    public void allowExtraKill() {
        extraKillAllowed = true;
        System.out.println(name + " 的青龙偃月刀效果触发：本回合可以额外使用一次“杀”！");
    }
    public boolean canUseKill() {
        return !hasUsedKill || extraKillAllowed;
    }


    // recover
    public void heal(int amount) {
        curHP = Math.min(maxHP, curHP + amount);
        System.out.println(name + " 恢复了 " + amount + " 点血量，当前血量：" + curHP);
    }


    /*Getter*/
    public Legend getLegend() {
        return legend;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public int getHP() { return curHP; }

    public Card getWeapon() {
        return weapon;
    }

    public Card getArmor(){
        return armor;
    }

    public boolean hasUsedKill() {
        return hasUsedKill;
    }

    /*Setter*/
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
        this.curHP = Math.max(curHP, maxHP);
    }

    public void setCurrentHP(int currentHP) { this.curHP = currentHP; }

    public void setHasUsedKill(boolean b) {
        this.hasUsedKill = b;
    }


//    public void setMaxShaCount(int maxShaCount) { this.maxShaCount = maxShaCount; }
//    public void setShaCount(int shaCount) { this.shaCount = shaCount; }


}
