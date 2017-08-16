/*
    A peak element is an element that is greater than its neighbors.

    Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

    The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

    You may imagine that num[-1] = num[n] = -∞.
 */

/*
    For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */

//////////////////////////////////////////
// Love & Understanding & Possibilities //
//////////////////////////////////////////

/*
    Note that PEAK could be more than 1 in an array, whereas there is only 1
    MAX.
 */
class Solution {

    public int findPeakElement(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = A.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid - 1] == A[mid + 1] && A[mid - 1] < A[mid] && A[mid + 1] <
                A[mid]) { /* Peak is the mid */
                return mid;
            /* Increasing, go right */
            } else if (A[mid - 1] < A[mid] && A[mid] < A[mid + 1]) {
                start = mid;
            } else { /* Decreasing, go left */
                end = mid;
            }
        }
        
        return A[start] > A[end] ? start : end; /* The larger index*/
    }
}