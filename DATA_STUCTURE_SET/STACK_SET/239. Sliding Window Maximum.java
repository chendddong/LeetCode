/*
    Given an array nums, there is a sliding window of size k which is moving
    from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 */

/*
    For example,
    Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
    Therefore, return the max sliding window as [3,3,5,5,6,7].

    Note: 
    You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

    Follow up:
    Could you solve it in linear time?
 */

////////////////////////
// ArrayDeque Version //        AC
////////////////////////

/*
    The time complexity for this version is just O(n)
    And the space complexity is O(k)
    
    It's sort like a decreasing stack because we are getting the max number of
    the window. The element in the array just go in and out of the deque once no matter what. So the worst case time complexity is O(2N)
 */

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {


        if (nums.length == 0) return new int[0]; /* Edge */
        
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        /* Put first k - 1 number in the deque */
        for (int i = 0; i < k - 1; i++) {
            putNumberInDeque(deque, nums[i]);
        }

        int index = 0;
        for (int i = k - 1; i < nums.length; i++) {
            putNumberInDeque(deque, nums[i]);
            ans[index++] = deque.peekFirst();
            getNumberOutDeque(deque, nums[i - k + 1]);
        }
        return ans;
    }

    /* Walkthrough */

    // [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]
    // 
    // ans = [7, 7, 8] 
    // 
    //                   deque:
    //                   
    //          |     1  2  7  7  8  | 
    //                x  x  x  x  
    //                
    //      first(front)         last(end)     
                                                        
    /* Always put the largest number at the front of the deque */
    private void putNumberInDeque(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offer(num);
    }
    /* 
        Poll the first number of the window if the peekFirst() is equal to the
        last number of the window. 
     */
    private void getNumberOutDeque(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }               
}

////////////////////////////////////////
// Sliding and Sort window O(n*klogk) //      Got AC in here
////////////////////////////////////////

public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || nums.length == 0) return new int
            [0];

        int[] res = new int[nums.length - k + 1];
        
        int i; 
        int j = 0; /* for res */
        for (i = 0; i <= nums.length - k; i++) {
            res[j++] = getMax(nums, i, i + k - 1);
        } 

        return res;
    }
    private int getMax(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(nums[i], max);
        }
        return max;
    }
}


/////////////////////
// TreeSet version //           AC
/////////////////////


/*
    We could use the heap to do the work, but the running time for the remove is
    not good(O(n)) as the TreeSet(O(log n)). We can use Node data structure to avoid the duplicates in the array.
    
    We will rewrite the comparator to compare the value of the Node, if they are equal, just compare them with the id.
    
    The total time is O(n log k)
 */
 
class Node implements Comparable<Node> {
    int id;
    int val;
    Node(int ID, int VAL) {
        id = ID;
        val = VAL;
    }
    public int compareTo(Node other) {
        Node a = (Node) other;
        if (this.val == a.val) {
            return this.id - a.id;
        }
        return this.val - a.val;
    }
}

public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || nums.length == 0 || nums == null) return new int[0];

        int n = nums.length;
        int[] res =  new int[n - k + 1];
        TreeSet<Node> maxHeap = new TreeSet<>();

        for (int i = 0; i < k - 1; i++) {
            maxHeap.add(new Node(i, nums[i]));
        }
        int index = 0;
        for (int i = k - 1; i < n; i++) {
            maxHeap.add(new Node(i, nums[i]));
            res[index++] = maxHeap.last().val;
            maxHeap.remove(new Node(i - k + 1, nums[i - k + 1]));
        }

        return res;
    }
}