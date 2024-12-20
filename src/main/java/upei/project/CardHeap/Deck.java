package upei.project.CardHeap;
import upei.project.Card.Card;
import upei.project.Card.BasicCard.*;
import upei.project.Card.EquipCard.Armor.*;
import upei.project.Card.EquipCard.Weapon.*;
import upei.project.Card.TacticCard.*;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Card> discardPile;

    public Deck() {
        cards = new ArrayList<>();
        discardPile = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() {
        int maxNumber = 9; // Card numbers 0-9

        // Add Basic Card
        for (int i = 0; i <= maxNumber; i++) {

            cards.add(new Kill(i));//Sha
            cards.add(new Flash(i));//Shan
            cards.add(new Jiu(i));//Jiu
            cards.add(new Tao(i));//Tao
        }

        // Add Equipment Card
        for (int i = 0; i <= maxNumber; i++) {
//
//                cards.add(new Card("Equip", "八卦阵", "50%几率闪避杀", i));
            cards.add(new RenWangShield(i));
//                cards.add(new Card("Equip", "杀无效", "完全免疫杀", i));
//
            cards.add(new GreenDragonCrescentBlade(i));
//                cards.add(new Card("Equip", "丈八蛇矛", "可将两张牌当作杀",  i));
//                cards.add(new Card("Equip", "雌雄双股剑", "命中对手时可以弃置对方一张牌",  i));
//
        }

        // Add Tactic Card
        for (int i = 0; i <= maxNumber; i++) {
            cards.add(new Duel(i));
            cards.add(new Nullify(i));
            cards.add(new Steal(i));
            cards.add(new DestroyBridge(i));
            cards.add(new PeachGarden(i));
            cards.add(new Harvest(i));
        }
    }
    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty!");
            return null;
        }
        return cards.remove(0);
    }

    public void discardCard(Card card) {
        discardPile.add(card);
        System.out.println("Card " + card.getName() + " is discarded to the discard pile!");
    }

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
    // Remove a specific card from the deck
    public boolean removeCard(Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
            System.out.println("Cards removed from the deck:" + card.getName());
            return true;
        } else {
            System.out.println("The specified card was not found in the deck:" + card.getName());
            return false;
        }
    }
    /*Getter*/
    public ArrayList<Card> getCards(){
        return this.cards;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }


}

