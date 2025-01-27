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
    public static long countWays(int target, int[] coins) {
        Arrays.sort(coins);
        sortedCoins = coins;
//        System.out.println(Arrays.toString(sortedCoins));
        return 0;
        // One coin first, continue down that path with other coins
        // After that's done restrict paths to things that won't lead to duplicates
        // If one coin is a factor of another, no need to continue with that
    }

    public static long fillCoins(int target, long sum, int ways) {
        if (sum == target) {
            return ways;
        }

        fillCoins(target, sum+sortedCoins[0], ways+1);


        return 0;
    }
}
