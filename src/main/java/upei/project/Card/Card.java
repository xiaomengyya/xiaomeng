package upei.project.Card;
import upei.project.People.Person;

public class Card {
    private final int number;
    private boolean isTaken = false;
    private Person owner = null;
    private Person target = null;
    public Card(int number) {
        this.number = number;
    }
    public int getNumber() {
        return this.number;
    }
    public void setOwner(Person player) {
        this.owner = player;
    }
    public Person getOwner() {
        return this.owner;
    }
    public void setTaken(boolean taken) {
        this.isTaken = taken;
    }
    public boolean isTaken() {
        return this.isTaken;
    }
    public boolean isNotTaken() {
        return !this.isTaken;
    }
    public void setTarget(Person target) {
        this.target = target;
    }
}
