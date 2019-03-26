package edu.bloomu.ch6c;
  
  import java.util.concurrent.ThreadLocalRandom;
  
  /**
   * Calculates the probability of winning the game of Craps and the expected length of a
   * game.
   *
   * @author Drue Coles
  */
 public class CrapsProbabilityCalculator3 {

     private static int wins = 0;
     private static int numRolls = 0; // total number of rolls in all games played

     public static void main(String[] args) {

         int games = 10_00_00; // number of games to simulate
         for (int i = 0; i < games; i++) {
            playCraps();
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
      * Plays the game of craps.
      */
     private static void playCraps() {
 
         final int firstSum = rollDice();
 
        if (firstSum == 7 || firstSum == 11) {
             wins++;
             return;
         }
 
        if (firstSum == 2 || firstSum == 3 || firstSum == 12) {
            return;
         }
 
         int newSum = rollDice();
        while (newSum != firstSum && newSum != 7) {
             newSum = rollDice();
        }
 
         if (newSum == firstSum) {
             wins++;
         }
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
         numRolls++;
         return die1 + die2;
     }
 }