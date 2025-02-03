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
    public static int numWays = 0;
    public static long countWays(int target, int[] coins) {
        // Sort to get coins in descending order
        Arrays.sort(coins);
        int[] ascendingSortedCoins = coins;
        sortedCoins = new int[ascendingSortedCoins.length];
        for (int i = ascendingSortedCoins.length-1; i >= 0; i--) {
            sortedCoins[ascendingSortedCoins.length-i-1] = ascendingSortedCoins[i];
        }
        long sum = 0;
        for (int i = 0; i < sortedCoins.length; i++) {
            sum += findCoinCombinations(target, sortedCoins, 0, 0);
        }
//        System.out.println(Arrays.toString(sortedCoins));
//        return fillCoins(target, 0, ascendingSortedCoins);
        // One coin first, continue down that path with other coins
        // After that's done restrict paths to things that won't lead to duplicates
        // If one coin is a factor of another, no need to continue with that
        return sum;
    }

//    public static long fillCoins(int target, long ways, int[] coins) {
//        if (target < 0) {
//            return 0;
//        }
//        if (target == 0) {
//            return 1;
//        }
//
//        for (int i = 0; i < coins.length; i++) {
//            long output = fillCoins(target-coins[i], ways+1, coins);
//            if (output != 0) {
//                break;
//            }
//        }
//
//    }

    public static long findCoinCombinations(int largeCoin, int[] smallCoins, int count, int index) {
        if (index >= smallCoins.length) {
            return 0;
        }
        count += largeCoin / smallCoins[index];
        if (largeCoin % smallCoins[index] == 0) {
            return count;
        }

        return findCoinCombinations(largeCoin % smallCoins[index], smallCoins, count+1, index+1);
    }
}
