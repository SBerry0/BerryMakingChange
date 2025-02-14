import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Sohum Berry
 */

public class MakingChange {
    // Initializing global variables
    public static int[] sortedCoins;
    public static long[][] memoizationBoard;
    public static long[][] tabulationBoard;

    public static long countWays(int target, int[] coins) {
        // Sort to get coins in descending order
        Arrays.sort(coins);
        sortedCoins = new int[coins.length];
        for (int i = coins.length-1; i >= 0; i--) {
            sortedCoins[coins.length-i-1] = coins[i];
        }

        // Initialize dynamic programming storage systems
        memoizationBoard = new long[coins.length][target+1];
        tabulationBoard = new long[coins.length][target+1];
        for (int i = 0; i < coins.length; i++) {
            memoizationBoard[i][0] = 1;
            tabulationBoard[i][0] = 1;
        }
        // Can get free information if the lowest number is 1, and fill the board up preemptively
        if (coins[0] == 1) {
            for (int i = 0; i < target; i++) {
                memoizationBoard[0][i] = 1;
                tabulationBoard[0][i] = 1;
            }
        }
        // Switch commented line to run each version
        return countWaysMemoization(target, 0);
//        return countWaysTabulation();
    }

    public static long countWaysMemoization(int target, int index) {
        // Unsuccessful base cases
        if (sortedCoins.length == index) {
            return 0;
        }
        if (target < 0) {
            return 0;
        }
        // Successful base cases
        if (target == 0) {
            return 1;
        }if (memoizationBoard[sortedCoins.length-index-1][target] != 0) {
            return memoizationBoard[sortedCoins.length-index-1][target];
        }

        // Recursing to both include and exclude the greatest available coin
        long include = countWaysMemoization(target - sortedCoins[index], index);
        long exclude = countWaysMemoization(target, index+1);

        // Fill the memoization board for future reference
        memoizationBoard[sortedCoins.length-index-1][target] = include + exclude;
        return include + exclude;
    }

    public static long countWaysTabulation() {
        // Starting bottom, then going up
        for (int i = 0; i < tabulationBoard.length; i++) {
            for (int j = 1; j < tabulationBoard[0].length; j++) {
                long exclude;
                long include;
                // Add the squares that correspond to including a coin and excluding a coin together
                try {
                    exclude = tabulationBoard[i-1][j];
                } catch (IndexOutOfBoundsException e) {
                    // Add 0 if the square doesn't exist
                    exclude = 0;
                }
                try {
                    include = tabulationBoard[i][j - sortedCoins[i]];
                } catch (IndexOutOfBoundsException e) {
                    include = 0;
                }
                tabulationBoard[i][j] = include+exclude;
            }
        }
        // Return the bottom right square which corresponds with the solution
        return tabulationBoard[tabulationBoard.length-1][tabulationBoard[0].length-1];
    }
}
