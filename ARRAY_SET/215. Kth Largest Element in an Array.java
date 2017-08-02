/**
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 *
 * -- Leet 215
 * -- Lint 5
 */

/*
    For example,
    Given [3,2,1,5,6,4] and k = 2, return 5.

    Note: 
    You may assume k is always valid, 1 ? k ? array's length.
 */

////////////////////////
// QuickSort Directly //
////////////////////////

class Solution {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return Integer.MIN_VALUE;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int start, int end) {

        int i = start;
        int j = end;
        int pivot = nums[(i + j) / 2];
        
        while (i <= j) {
            while (nums[i] < pivot && i <= j) {
                i++;
            }
            while (nums[j] > pivot && i <= j) {
                j--;
            }
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        /* !These babies should be outside the while loop! */
        if (start < j) {
            quickSort(nums, start, j);    
        }
        if (i < end) {
            quickSort(nums, i, end);    
        }        
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

        /* Walk through: Use MinHeap to get the kth largest */
        // [9,3,2,4,8], the 3rd largest element is 4.
        //     9  add(9)

        //     9           3
        //    /     ->    /            add(3)
        //   3           9

        //     3           2
        //    / \   ->    / \          add(2)
        //   9   2       9   3

        //     2           4           3
        //    / \         / \         / \           add(4) 
        //   9   3  ->   9   3  ->   9   4
        //  /           /
        // 4           2  
        //     3           3           4
        //    / \   ->    / \  ->     / \           add(8)
        //   9   4       9   4       9   8
        //              / 
        //             8   

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

/*
    PriorityQueue(int initialCapacity, Comparator<? super E> comparator):

    Creates a PriorityQueue with the specified initial capacity that orders its
    elements according to the specified comparator. So when we need to modify
    the order of the Heap, Pass both capacity and the comparator together.
 */

//////////////////////////////
// QuickSelect with Shuffle //
//////////////////////////////

public class Solution {
    /* 
        O(N) guaranteed running time + O(1) space by shuffling the input first,
        then do the quickSelect algorithm;

        This is quite important.
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

/* How to shuffle an array of numbers */