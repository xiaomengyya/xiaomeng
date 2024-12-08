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
    private boolean extraKillAllowed; // Is it allowed to use "kill" additionally?

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
        this.extraKillAllowed = false; // Additional use of "kill" is not allowed in the initial state
    }

    // reset round
    public void resetTurn() {
        hasUsedKill = false;
    }

    //Draw Card
    public void drawCard(Card card) {
        handCards.add(card);
    }

    //Has Card
    public boolean hasCard(String cardName) {
        return handCards.stream().anyMatch(c -> c.getName().equals(cardName));
    }

    //Assign legend randomly
    public void assignLegend(Legend legend) {
        this.legend = legend;
        System.out.println("The Legend assigned to + " + name + " is: " + legend);
    }

    // Equip Armor / Weapon(Play can only equip one armor and one weapon)
    public void equip(EquipCard equipCard) {
        if (equipCard instanceof Weapon) {
            if (weapon != null) {
                System.out.println(name + "currently equipped weapons:" + weapon.getName());
                System.out.println("Do you want to remove the current weapon and equip a new one? Enter 0 for yes, 1 for no:");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if (choice == 0) {
                    handCards.add(weapon);
                    System.out.println(name + " unequipped weapon：" + weapon.getName());
                    weapon = (Weapon) equipCard;
                    System.out.println(name + " equipped weapon：" + equipCard.getName());
                } else {
                    System.out.println(name + " retained the current weapon and no new weapons is equipped!");
                }
            } else {
                weapon = (Weapon) equipCard;
                System.out.println(name + " equipped weapon：" + equipCard.getName());
            }
        } else if (equipCard instanceof Armor) {
            if (armor != null) {
                System.out.println(name + " currently equip armor：：" + armor.getName());
                System.out.println("Do you want to remove the current armor and equip new one? Enter 0 for yes, 1 for no:");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if (choice == 0) {
                    handCards.add(armor);
                    System.out.println(name + " unequipped armor：" + armor.getName());
                    armor = (Armor) equipCard;
                    System.out.println(name + " equipped armor：" + equipCard.getName());
                } else {
                    System.out.println(name + " retained the current armor and no new armor is equipped!");
                }
            } else {
                armor = (Armor) equipCard;
                System.out.println(name + " equipped weapon：" + equipCard.getName());
            }
        }
    }

    //Unequip Weapon
    public void unequipWeapon() {
        if (weapon != null) {
            System.out.println(name + " unequipped weapon：" + weapon.getName());
            weapon = null;
        } else {
            System.out.println(name + " No weapons to unload!");
        }
    }
    //Unequip Armor
    public void unequipArmor() {
        if (armor != null) {
            System.out.println(name + " unequipped armor：" + armor.getName());
            armor = null;
        } else {
            System.out.println(name + ": No armor to remove!");
        }
    }


    // Take damage
    public void takeDamage(int damage) {
        if (armor != null && armor.getName().equals("RenWangSheild")) {
            System.out.println(name + " triggers the RenWang Shield effect, immune to normal kills!");
            return;
        }
        curHP -= damage;
        System.out.println(name + " get " + damage + " damage，current HP is：" + curHP);
        if (curHP <= 0) {
            System.out.println(name + " died in battle");
        }
    }

    public void allowExtraKill() {
        extraKillAllowed = true;
        System.out.println(name + " Green Dragon Crescent Blade effect is triggered and can be used again to kill!\"");
    }
    public boolean canUseKill() {
        return !hasUsedKill || extraKillAllowed;
    }


    // recover
    public void heal(int amount) {
        curHP = Math.min(maxHP, curHP + amount);
        System.out.println(name + " recover " + amount + " HP, current HP is" + curHP);
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




}
