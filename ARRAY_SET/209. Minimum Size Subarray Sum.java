/*
    Given an array of n positive integers and a positive integer s, find the
    minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

    For example, given the array [2,3,1,2,4,3] and s = 7,
    the subarray [4,3] has the minimal length under the problem constraint.
    
    -- LeetCode 209
    -- LintCode 406
 */

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
         // nums = [2,3,1,2,4,3] || s = 7
         //         i     j                min = 4
         //           i     j              min = 4
         //             i   j              min = 3
         //               i   j            min = 3
         //                 i j            min = 2
        for (i = 0; i < nums.length; i++) {
            /* Legal index and sum; Move the second pointer*/
            while (j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }

            /* Update the min */
            if (sum >= s) {
                min = Math.min(min, j - i);
            }

            /* Move the first pointer */
            sum -= nums[i];
        }

        if (min == Integer.MAX_VALUE) {
            min = 0;
        }
        return min;                       
    }
}