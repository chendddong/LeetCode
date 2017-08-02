/*
    Given an array consists of non-negative integers, your task is to count the
    number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 */

/*
    Example 1:
    Input: [2,2,3,4]
    Output: 3
    Explanation:
    Valid combinations are: 
    2,3,4 (using the first 2)
    2,3,4 (using the second 2)
    2,2,3
    Note:
    The length of the given array won't exceed 1000.
    The integers in the given array are in the range of [0, 1000].
 */

public class Solution {

    /* This is important because of the move of the pointer. */
    public int triangleNumber(int[] S) {
        /* Must be 3 or more numbers */
        if (S == null || S.length < 3) {
            return 0;
        }

        int count = 0;
        int left = 0;
        int right = S.length - 1;        
        Arrays.sort(S); /* Always sort an array */
        
        /* Walk through */
        // [3,4,6,7]
        //  l     
        //    r 
        //        i
        for (int i = 0; i < S.length; i++) {
            left = 0;
            right = i - 1;
            while (left < right) {
                if (S[left] + S[right] > S[i]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }

        }

        return count;
    }
}

/* 
    The code here is actually very concise and easy, the hardest thing is to think the matter through.

    If the time complexity is not less than nlogn we can just sort it using the generic sort method
*/