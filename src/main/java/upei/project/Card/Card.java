package upei.project.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

public abstract class Card {
    private int number;
    //private String type;
    private String info;
    private String name;
    private Person owner = null;
    private Person target = null;
    public Card(String name, String description, int number) {
        this.name = name;
        this.info = description;
        this.number = number;
    }
    /*Getter*/
    public int getNumber() {
        return this.number;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return this.info;
    }
    // Abstract method, card effects need to be implemented by subclasses
    public abstract void activateEffect(Person self, Person opponent, GameController game);
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
