package upei.project.Controller;
import upei.project.Card.Card;
import upei.project.Card.EquipCard.EquipCard;
import upei.project.CardHeap.Deck;
import upei.project.CardHeap.LegendsPool;
import upei.project.People.Legend;
import upei.project.People.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private static ArrayList<Person> players = new ArrayList<>();
    private static Person currentPerson = null;
    private static int round = 1;
    private Person p1;
    private Person p2;
    private Deck deck;
    private LegendsPool legendsPool;
    private Person unlimitedKillPlayer; // 当前允许无限次使用“杀”的玩家

    public GameController() {
        deck = new Deck();
        legendsPool = new LegendsPool();
        unlimitedKillPlayer = null; // 初始化为空
        initializeGame();

    }

    private void initializeGame() {

        System.out.println("Initializing the game...");
        //Creat Player
        p1 = new Person("Player 1", 4);
        p2 = new Person("Player 2", 4);

        // Assign Legends
        p1.assignLegend(legendsPool.assignRandomLegend());
        p2.assignLegend(legendsPool.assignRandomLegend());

        // Draw 5 cards at the beginning
        for (int i = 0; i < 5; i++) {
            p1.drawCard(deck.drawCard());
            p2.drawCard(deck.drawCard());
        }
        System.out.println("Game initialization completed!");
    }

    // 玩家回合逻辑
    public void startTurn(Person player) {
        System.out.println(player.getName() + "'s turn begins");
        player.resetTurn();

        // Whether to activate Legend skills
        useLegendSkill(player, getOpponent(player));

        //Draw card phase
        System.out.println(player.getName() + " draws 2 cards");
        player.drawCard(deck.drawCard());
        player.drawCard(deck.drawCard());

        // Card-playing phase
        playPhase(player, getOpponent(player));

        // Discard phase
        discardPhase(player);

        System.out.println(player.getName() + "'s turn ends");
    }

    // 使用 Legend 技能阶段
    private void useLegendSkill(Person player, Person opponent) {
        Legend legend = player.getLegend();
        if (legend == null) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(player.getName() + "'s Legend is：" + legend.getName());
        System.out.println("Skill Description: " + legend.getSkillDescription());
        System.out.println("Do you want to use the skill? Enter 1 to use it, enter 0 to skip it:");

        int choice = scanner.nextInt();
        if (choice == 1) {
            legend.activateSkill(player, opponent, this);
        } else {
            System.out.println(player.getName() + " skipped using the skill.");
        }
    }

    // 弃牌阶段（如果手牌数量超过当前血量）
    private void discardPhase(Person player) {
        while (player.getHandCards().size() > player.getHP()) {
            System.out.println(player.getName() + "'s hand cards:" + player.getHandCards());
            System.out.println("The number of cards in your hand exceeds your current HP. Please choose a card to discard (enter index):");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            if (index >= 0 && index < player.getHandCards().size()) {
                Card discardedCard = player.getHandCards().remove(index);
                deck.discardCard(discardedCard);
                System.out.println(player.getName() + " discards card：" + discardedCard);
            } else {
                System.out.println("Invalid index, please re-enter!");
            }
        }
    }

    private void playPhase(Person player, Person opponent) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(player.getName() + "'s hand card:'：" + player.getHandCards());
            System.out.println("Please enter the index of the card to be played (enter -1 to end playing):");
            int index = scanner.nextInt();

            if (index == -1) {
                break;
            }

            if (index >= 0 && index < player.getHandCards().size()) {
                Card card = player.getHandCards().get(index);

                if (card.getName().equals("Kill")) {
                    // Determine whether "kill" can be used
                    if (canUseKill(player)) {
                        player.setHasUsedKill(true);
                        player.getHandCards().remove(card);
                        System.out.println(player.getName() + " uses \"Kill\" to attack " + opponent.getName());
                        opponent.takeDamage(1);
                    } else {
                        System.out.println("\"Kill\" has been used in this round and cannot be used again!");
                    }
                } else if (card instanceof EquipCard) {
                    // 装备卡牌逻辑
                    player.equip((EquipCard) card);
                    player.getHandCards().remove(card);
                } else {
                    System.out.println("This card cannot be used, please choose again!");
                }
            } else {
                System.out.println("Invalid index, please re-enter!");
            }
        }
    }

    public void allowExtraKill(Person player) {
        player.allowExtraKill();
    }
    // Allow the specified player to use "Kill" unlimited times
    public void allowUnlimitedKills(Person player) {
        unlimitedKillPlayer = player;
        System.out.println(player.getName() + "can use \"Kill\" unlimited times in this round!");
    }
    // Check if the player can use "Kill"
    public boolean canUseKill(Person player) {
        if (unlimitedKillPlayer == player) {
            return true; // If the player is currently allowed to use "Kill" unlimited times, return true
        }
        return !player.hasUsedKill(); // Otherwise check if "kill" has been used
    }

    // 重置无限“杀”状态
    public void resetUnlimitedKills() {
        unlimitedKillPlayer = null;
    }

    public void removeCardFromDeck(Card card) {
        if (!deck.removeCard(card)) {
            System.out.println("An attempt to remove a card failed. The card may no longer exist in the deck.");
        }
    }

    //Use specific card
    public void playCard(Card card, Person self, Person opponent) {
        System.out.println(self.getName() + " uses the card" + card.getName());
        card.activateEffect(self, opponent, this);
    }

    // 获取对手
    private Person getOpponent(Person player) {
        return player == p1 ? p2 : p1;
    }

    // 弃置卡牌
    public void discardCard(Card card) {
        deck.discardCard(card);
        System.out.println("Card " + card.getName() + " is discarded to the discard pile!");
    }

    public Deck getDeck() {
        return this.deck;
    }

}