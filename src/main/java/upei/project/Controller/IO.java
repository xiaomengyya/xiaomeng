package upei.project.Controller;

import upei.project.Card.Card;

import java.util.ArrayList;
import java.util.Scanner;
public class IO {
    //private static final Scanner sn = new Scanner(System.in);
    public static void println(String s) {
        System.out.println(s);
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void printHandCards(ArrayList<Card> cards){
        for(int i = 0; i < cards.size(); i ++){
            println("[" + i + "]" + cards.get(i).getName() + "-" + cards.get(i).getNumber() + ": " + cards.get(i).getDescription());

        }
    }

}