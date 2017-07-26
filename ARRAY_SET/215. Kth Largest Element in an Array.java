/**
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 */

/*
    For example,
    Given [3,2,1,5,6,4] and k = 2, return 5.

    Note: 
    You may assume k is always valid, 1 ? k ? array's length.
 */

///////////////////
// Sort Directly //
///////////////////

public class Solution {
    /* O(nlgn) running time + O(1) memory */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

////////////////
// Use a Heap //
////////////////

public class Solution {
    /* O(n lg k) running time + O(k) memory */
    public int findKthLargest(int[] nums, int k) {
        /* Natural ordering */
        PriorityQueue<Integer> pq = new PriorityQueue<>(); 

        /* Add all integers */        
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}

//////////////////////////////
// Quick Select / Partition //
//////////////////////////////

public class Solution {
    /* 
        O(N) best case / O(N^2) worst case running time + O(1) memory.

        The smart approach for this problem is to use the selection algorithm 
        (based on the partition method - the same one as used in quickSort
     */
    public int findKthLargest(int[] nums, int k) {
        /* The return index */
        k = nums.length - k;
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int j = partition(nums, start, end);
            if (j < k) {
                start = j + 1;
            } else if (j > k) {
                end = j - 1;
            } else {
                break;
            }
        }

        return nums[k];
    }

    private int partition(int[] a, int start, int end) {
        int i = start;
        int j = end + 1;

        while (true) {
            while (i < end && less(a[++i], a[start]));
            while (j > start && less(a[start], a[--j]));
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, start, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }
}

////////////////////////////
// QuickSort with Shuffle //
////////////////////////////

public class Solution {
    /* 
        O(N) guaranteed running time + O(1) space by shuffling the input first,
        then do the quickSelect algorithm;
     */
    public int findKthLargest(int[] nums, int k) {
        
        /* Shuffle the number to achieve O(n) running time */
        shuffle(nums);

        /* The return index */
        k = nums.length - k;
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int j = partition(nums, start, end);
            if (j < k) {
                start = j + 1;
            } else if (j > k) {
                end = j - 1;
            } else {
                break;
            }
        }

        return nums[k];
    }

    private int partition(int[] a, int start, int end) {
        int i = start;
        int j = end + 1;

        while (true) {
            while (i < end && less(a[++i], a[start]));
            while (j > start && less(a[start], a[--j]));
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, start, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

    private void shuffle(int[] a) {
        Random random = new Random();
        for (int ind = 1; ind < a.length; ind++) {
            int r = random.nextInt(ind + 1);
            swap(a, ind, r);
        }
    }
}