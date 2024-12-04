package upei.project.Card;
import upei.project.People.Person;

public class Card {
    private final int number;
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
    public void setTarget(Person target) {
        this.target = target;
    }
    public Person getTarget() {
        return this.target;
    }
}
