package upei.project.Card.BasicCard;

public class Tao extends BasicCard {
    public Tao(int number) {
        super(number);
    }

    public Object use() {
        getTarget().recover(1);
        return true;
    }

    public String toString() {
        return "Tao";
    }
}
