package upei.project.Controller;
import upei.project.Card.Card;
import upei.project.Card.EquipCard.EquipCard;
import upei.project.Card.TacticCard.TacticCard;
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
    private Person unlimitedKillPlayer; // Players who are currently allowed to use "Kill" unlimited times

    public GameController() {
        deck = new Deck();
        legendsPool = new LegendsPool();
        unlimitedKillPlayer = null;
        initializeGame();

    }

    public void initializeGame() {

        System.out.println("Initializing the game...");
        //Creat Player
        p1 = new Person("Player 1", 4);
        p2 = new Person("Player 2", 4);

        // Assign Legends
        p1.assignLegend(legendsPool.assignRandomLegend());
        p2.assignLegend(legendsPool.assignRandomLegend());

        System.out.println("Player 1 Legend: " + p1.getLegend().getName());
        System.out.println("Player 2 Legend: " + p2.getLegend().getName());

        // Draw 5 cards at the beginning
        for (int i = 0; i < 5; i++) {
            p1.drawCard(deck.drawCard());
            p2.drawCard(deck.drawCard());
        }
        IO.println("Player 1 Hand Cards: " );
        IO.printHandCards(p1.getHandCards());
        IO.println("Player 2 Hand Cards: "  );
        IO.printHandCards(p2.getHandCards());
        IO.println("Game initialization completed!");
    }

    // Player turn logic
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

    // Use Legend Skill Phase
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

    //Discard phase (if the number of cards in hand exceeds the current health)
    private void discardPhase(Person player) {
        while (player.getHandCards().size() > player.getHP()) {
            IO.println(player.getName() + "'s hand cards:");
            IO.printHandCards(player.getHandCards());
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
        GameController gc = new GameController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(player.getName() + "'s hand card:'：");
            IO.printHandCards(player.getHandCards());
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
                    player.equip((EquipCard) card);
                    player.getHandCards().remove(card);
                    IO.println("Equip" + card.getName() + "successfully!");
                } else if(card instanceof TacticCard){
                    card.activateEffect(player, opponent, gc);
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

    // Reset infinite "kill" status
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
    public Person getOpponent(Person player) {
        return player == p1 ? p2 : p1;
    }

    //Get current player
    public Person getCurrentPlayer() {
        return round % 2 == 1 ? p1 : p2; // Odd rounds are p1, even rounds are p2
    }
    public int getRound() {
        return round;
    }
    public Person getP1() {
        return p1;
    }

    public Person getP2() {
        return p2;
    }
    public Deck getDeck() {
        return this.deck;
    }

    public void nextTurn() {
        round++;
        System.out.println("\n--- Next Turn ---");
        if (round % 2 == 1) {
            System.out.println("It's " + p1.getName() + "'s turn.");
        } else {
            System.out.println("It's " + p2.getName() + "'s turn.");
        }
    }


    // Discard Card
    public void discardCard(Card card) {
        deck.discardCard(card);
        System.out.println("Card " + card.getName() + " is discarded to the discard pile!");
    }



    public void runGame() {
        System.out.println("Starting the game...");

        // 游戏主要逻辑
        while (p1.getHP() > 0 && p2.getHP() > 0) {
            System.out.println("\n--- Round " + round + " ---");

            // Player 1's turn
            startTurn(p1);
            if (p2.getHP() <= 0) {
                System.out.println(p2.getName() + " has been defeated!");
                break;
            }

            // Player 2's turn
            startTurn(p2);
            if (p1.getHP() <= 0) {
                System.out.println(p1.getName() + " has been defeated!");
                break;
            }
            round++; // Increment round counter
        }

        // 游戏结束
        System.out.println("\nGame Over!");
        if (p1.getHP() > 0) {
            System.out.println(p1.getName() + " is the winner!");
        } else if (p2.getHP() > 0) {
            System.out.println(p2.getName() + " is the winner!");
        }
    }


}