package upei.project.Card.TacticCard;

import upei.project.Controller.GameController;
import upei.project.People.Person;

//Jue Dou
public class Duel extends TacticCard {
    private int num;
    public Duel(int num) {
        super("Due", "Two players take turns to \"Kill\". The player who cannot \"Kill\" will receive 1 point of damage.", num);
    }

    @Override
    public void activateEffect(Person self, Person opponent, GameController game) {
        System.out.println(self.getName() + " used Duel, the target was " + opponent.getName());
        boolean selfCanKill, opponentCanKill;
        while (true) {
            selfCanKill = self.getHandCards().stream().anyMatch(card -> card.getName().equals("Kill"));
            if (!selfCanKill) {
                System.out.println(self.getName() + " is unable to play \"Kill\" card, taking 1 damage!");
                self.takeDamage(1);
                break;
            } else {
                System.out.println(self.getName() + " play \"Kill\"！");
                self.getHandCards().removeIf(card -> card.getName().equals("Kill"));
            }

            opponentCanKill = opponent.getHandCards().stream().anyMatch(card -> card.getName().equals("Kill"));
            if (!opponentCanKill) {
                System.out.println(opponent.getName() + "is unable to play \"Kill\" card, taking 1 damage!");
                opponent.takeDamage(1);
                break;
            } else {
                System.out.println(opponent.getName() + " play \"Kill\"！");
                opponent.getHandCards().removeIf(card -> card.getName().equals("Kill"));
            }
        }
    }
}
