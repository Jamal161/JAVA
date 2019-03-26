package edu.bloomu.ch6c;
 
  import java.util.concurrent.ThreadLocalRandom;
  
  /**
   * Calculates the probability of winning the game of Craps and the expected length of a
   * game.
   *
   * @author Drue Coles
  */
 public class CrapsProbabilityCalculator2 {
    public static void main(String[] args) {
 
         final int games = 1_000_00; // number of games to simulate
         int wins = 0;
         int numRolls = 0;  // total number of rolls in all games played
      
         // simulate game many times
         for (int i = 0; i < games; i++) {
            int result = playCraps();
             if (result > 0) {
                 wins++;
             }
             numRolls += Math.abs(result);
         }
 
         // Calculate results and construct output strings.
         String s1 = String.format("Win rate: %,d/%,d", wins, games);
 
         double winPercent = (double) wins / games * 100;
        String s2 = String.format("Winning percentage: %.3f%%", winPercent);
         
         double expectedLength = (double) numRolls / games;
        String s3 = String.format("Expected length of game: %.3f rolls", expectedLength);
 
         String output = s1 + "\n" + s2 + "\n" + s3;
         System.out.println(output);
     }

     /**
      * Plays the game of Craps.
      *
      * @return the number of rolls (positive if player wins, negative if player loses)
      */
     private static int playCraps() {
        final int firstSum = rollDice();
 
        if (firstSum == 7 || firstSum == 11) {
             return 1; // positive for winning
         }
 
         if (firstSum == 2 || firstSum == 3 || firstSum == 12) {
             return -1; // negative for losing
        }

         int numRolls = 1;
         do {
            int newSum = rollDice();
             numRolls++;
             if (newSum == firstSum) {
                 return numRolls;
             }
            if (newSum == 7) {
                 return -numRolls;
             }
         } while (true);
    }

     /**
      * Rolls a pair of dice.
      *
      * @return the sum of the numbers rolled
      */
     private static int rollDice() {
         ThreadLocalRandom rand = ThreadLocalRandom.current();
         int die1 = 1 + rand.nextInt(6);
         int die2 = 1 + rand.nextInt(6);
         return die1 + die2;
    }
 }