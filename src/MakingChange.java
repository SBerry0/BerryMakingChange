import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Sohum Berry
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static int[] sortedCoins;
    public static long[][] memoizationBoard;

    public static long[][] tabulationBoard;

    public static long countWays(int target, int[] coins) {
        // Sort to get coins in descending order
        Arrays.sort(coins);
        int[] ascendingSortedCoins = coins;
        sortedCoins = new int[ascendingSortedCoins.length];
        for (int i = ascendingSortedCoins.length-1; i >= 0; i--) {
            sortedCoins[ascendingSortedCoins.length-i-1] = ascendingSortedCoins[i];
        }

        memoizationBoard = new long[coins.length][target+1];
        tabulationBoard = new long[coins.length][target+1];
        for (int i = 0; i < coins.length; i++) {
            memoizationBoard[i][0] = 1;
            tabulationBoard[i][0] = 1;
        }
        if (ascendingSortedCoins[0] == 1) {
            for (int i = 0; i < target; i++) {
                memoizationBoard[0][i] = 1;
                tabulationBoard[0][i] = 1;
            }
        }
        long mem = countWaysMemoization(target, 0);
        long tab = countWaysTabulation();
        return tab;
    }

    public static long countWaysMemoization(int target, int index) {
        if (sortedCoins.length == index) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        if (memoizationBoard[sortedCoins.length-index-1][target] != 0) {
            return memoizationBoard[sortedCoins.length-index-1][target];
        }

        long include = countWaysMemoization(target - sortedCoins[index], index);
        long exclude = countWaysMemoization(target, index+1);

        memoizationBoard[sortedCoins.length-index-1][target] = include + exclude;
        return include + exclude;
    }

    public static long countWaysTabulation() {
        for (int i = 0; i < tabulationBoard.length; i++) {
            for (int j = 0; j < tabulationBoard[0].length; j++) {
                System.out.print(tabulationBoard[i][j]);
                long exclude;
                long include;
                try {
                    exclude = tabulationBoard[i-1][j];
                } catch (IndexOutOfBoundsException e) {
                    exclude = 0;
                }
                try {
                    include = tabulationBoard[i][j - sortedCoins[i]];
                } catch (IndexOutOfBoundsException e) {
                    include = 0;
                }
                tabulationBoard[i][j] = include+exclude;
            }
            System.out.println("");
        }
        return tabulationBoard[tabulationBoard.length-1][tabulationBoard[0].length-1];
    }
}
