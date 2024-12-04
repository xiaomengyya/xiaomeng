package upei.project.People;
import upei.project.Controller.IO;
import upei.project.Card.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    private final String name;
    private int maxHP;
    private int curHP;

    private boolean myRound = false;
    private int maxShaCount = 1;
    private int shaCount = maxShaCount;
    private boolean hasUsedSkill1 = false;

    //private ArrayList<Card> curCards;
    private final ArrayList<Card> cards = new ArrayList<>();
    private final HashMap<EquipType, Equipment> equipments = new HashMap<>();


//    public Person(String name, int maxHP, int curHP) {
//        this.name = name;
//        this.curHP = curHP;
//        this.maxHP = maxHP;
//    }

    public Person(String name, int maxHP){
        this.name = name;
        this.curHP = maxHP;
        this.maxHP = maxHP;
    }

    public boolean isMyRound() { return myRound; }

    public void setMyRound(boolean myRound) { this.myRound = myRound; }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
        this.curHP = Math.max(curHP, maxHP);
    }

    public int getMaxHP() { return maxHP; }

    public void setShaCount(int shaCount) { this.shaCount = shaCount; }

    public int getShaCount() { return shaCount; }

    public void setCurrentHP(int currentHP) { this.curHP = currentHP; }

    public int getHP() { return curHP; }

    public int getMaxShaCount() { return maxShaCount; }

    public void setMaxShaCount(int maxShaCount) { this.maxShaCount = maxShaCount; }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public HashMap<EquipType, Equipment> getEquipments() {
        return equipments;
    }
    public boolean hasEquipment(EquipType type, String name) {
        if (name == null) {
            return equipments.get(type) != null;
        }
        if (equipments.get(type) == null) {
            return false;
        }
        return equipments.get(type).toString().equals(name);
    }

    public void subCurrentHP(int sub) { curHP -= sub; }

    //Add Card
    public void addCard(ArrayList<Card> cs, boolean print) {
        IO.println(this + " got " + cs.size() + " cards");
        for (Card c : cs) {
            getCards().add(c);
            if (print) {
                IO.printToIO(this + " got card: ");
                IO.printCard(c);
            }
            c.setOwner((Person) this);
        }
    }

    public void addCard(Card c, boolean print) { //Add one card
        getCards().add(c);
        if (print) {
            IO.println(this + " got 1 card");
            IO.printToIO(this + " got card: ");
            IO.printCard(c);
        }
        c.setOwner((Person) this);
    }

    //Draw Card
    public void drawCard() {
        addCard(CardsHeap.draw());
    }

    public void drawCards(int num, boolean print) {
        addCard(CardsHeap.draw(num), print);
    }

    public void drawCards(int num) {
        drawCards(num, true);
    }
}
