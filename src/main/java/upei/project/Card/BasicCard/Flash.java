package upei.project.Card.BasicCard;

public class Flash extends BasicCard {
    public Flash(int number) {
        super(number);
    }

    public Object use() {
        return true;
    }

    public String toString() {
        return "Flash";
    }
}
