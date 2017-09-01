/*
    You are a professional robber planning to rob houses along a street. Each
    house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

/////////////////
// DP template //
/////////////////

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        /* State : dp[i] from index 0 to ith, the max money we can rub */
        // long[] dp = new long[n + 1]; /* Watch out the long */
        int[] dp = new int[2];

        /* Init */
        dp[0] = 0;
        dp[1] = nums[0];   

        /* Function */
        for (int i = 2; i <= n; i++) {
            dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + nums[i - 1]);
        }

        /* Answer */
        return dp[n % 2];        
    }
}