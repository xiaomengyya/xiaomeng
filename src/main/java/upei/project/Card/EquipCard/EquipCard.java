package upei.project.Card.EquipCard;

import upei.project.Card.Card;

public class EquipCard extends Card {
    private final String type;
    public EquipCard(int number, String type) {
        super(number);
        this.type = type;
    }
}
