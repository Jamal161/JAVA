
package javaapplication5;

import java.util.Random;
import java.util.Scanner;


public class JavaApplication5 {

   public static void main(String[] args) throws java.lang.Exception {
  int wins = 0;
  int loss = 0;
  int n;
  
  Scanner input = new Scanner(System.in);
		
		System.out.println("Enter number of round:");
		n= input.nextInt();

  for (int i = 0; i < n; i++) {
    System.out.println("roll the dices");
    int score = roll();
    System.out.println("\n score " + score);

    if (score == 7 || score == 11) {
      System.out.println("\n Score = " + score);
      System.out.println("you win");
      wins = wins + 1;
    } else if (score == 2 || score == 3 || score == 12) {
      System.out.println("\n Score = " + score);
      System.out.println("you lose");
      loss = loss + 1;
    } else {
      int point = score;
      System.out.println("\n Point = " + point);
      while (true) {
        score = roll();
        System.out.println("\n Score new = " + score);
        if (score == point) {
          System.out.println("\n you win");
          wins = wins + 1;
          break;
        }
        if (score == 7) {
          System.out.println("\n you lose");
          loss = loss + 1;
          break;
        }
      }
    }
  }

  System.out.println("\n number of wins = " + wins
      + " and number of loss = " + loss +
      " and the probability for winning a game = " + (double) wins / (wins + loss));
}


  public static int roll() {
  Random randomGenerator = new Random();
  int dice1 = randomGenerator.nextInt(6) + 1;
  int dice2 = randomGenerator.nextInt(6) + 1;
  System.out.println("\n dice1 = " + dice1 + " dice2 = " + dice2);
  return dice1 + dice2;
}
}