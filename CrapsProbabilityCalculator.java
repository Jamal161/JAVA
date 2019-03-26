package edu.bloomu.ch6c;
  
  import java.util.concurrent.ThreadLocalRandom;
  
  /**
   * Calculates the probability of winning the game of Craps. 
   *
   * @author Drue Coles
   */
 public class CrapsProbabilityCalculator {
 
     public static void main(String[] args) {
 
        int games = 1_000_00; // number of games to simulate
       int wins = 0;
        for (int i = 0; i < games; i++) {
             if (playerWins()) {
                 wins++;
             }
        }
 
        // Calculate result and construct output strings.
        String s1 = String.format("Win rate: %,d/%,d", wins, games);
         
         double winPercent = (double) wins / games * 100;
         String s2 = String.format("Winning percentage: %.3f%%", winPercent);
         
         String output = s1 + "\n" + s2;
         System.out.println(output);
     }
 
     /**
      * Simulates the game of Craps.
      *
      * @return true if player wins, false otherwise
      */
     public static boolean playerWins() {
         final int firstSum = rollDice();
 
         if (firstSum == 7 || firstSum == 11) {
            return true;
         }
         if (firstSum == 2 || firstSum == 3 || firstSum == 12) {
            return false;
         }

        int newSum = rollDice();
         while (newSum != firstSum && newSum != 7) {
             newSum = rollDice();
         }
         return newSum == firstSum;
    }

    /**
      * Simulates rolling a pair of dice.
     *
      * @return the sum of numbers rolled
      */
     public static int rollDice() {
         ThreadLocalRandom rand = ThreadLocalRandom.current();
         int die1 = 1 + rand.nextInt(6);
         int die2 = 1 + rand.nextInt(6);
         return die1 + die2;
     }
}