/**
 * Given an integer array with no duplicates. A maximum tree building on this
 * array is defined as follow:
 */

/*
    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
    Construct the maximum tree by the given array and output the root node of this tree.

    Example 1:
    Input: [3,2,1,6,0,5]
    Output: return the tree root node representing the following tree:

          6
        /   \
       3     5
        \    / 
         2  0   
           \
            1
    Note:
    The size of the given array will be in the range [1,1000].
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//////////////////////////////
// Recursive by finding max //
//////////////////////////////

/*
    Straightforward recursive:   
    
    Time complexity : O(n^2)
    ​
    The function construct is called n times. At each level of the recursive
    tree, we traverse over all the n elements to find the maximum element. In the average case, there will be a log(n) levels leading to a complexity of O(nlog(n)). In the worst case, the depth of the recursive tree can grow upto nn, which happens in the case of a sorted nums array, giving a complexity of O(n^2)

    Space complexity : O(n).The size of the set can grow up to n in the worst
    case. In the average case, the size will be log(n) for n elements in nums, giving an average case complexity of O(log(n)) 
 */
public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] A) {
        return construct(A, 0, A.length); /* Narrow down the index */
    }
    private TreeNode construct(int[] nums, int l, int r) {
        if (l == r) return null; /* Base */
        int maxIndex = max(nums, l, r); /* Find the max index */
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = construct(nums, l, maxIndex); /* Go left */
        root.right = construct(nums, maxIndex + 1, r); /* Go right */
        return root;
    }
    private int max(int[] nums, int l, int r) {
        int maxIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}




/////////////////////////////////////
// Using TreeNode Array as a Stack //
/////////////////////////////////////

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] A) {
        int len = A.length;
        TreeNode[] stack = new TreeNode[len];
        for (int i = 0; i < len; i++) {
            stack[i] = new TreeNode(0);
        }
        /* Walkthrough */

        // A = [2, 5, 6, 0, 3, 1]
        // 
        // stack [0', 0', 0', 0', 0', 0'       count = 0,
        // temp = 2'
        // 
        // stack [2', 0', 0', 0', 0', 0'       count = 1,
        // temp = 5'
        // 
        // TREE:
        //        5'
        //       /
        //      2'                        count = 0
        //      
        // stack [5', 0', 0', 0', 0', 0'  count = 1                          
        // temp = 6'
        // 
        // TREE:
        //          6'
        //         /
        //        5'
        //       /
        //      2'                        count = 0
        //      
        // stack [6', 0', 0', 0', 0', 0'  count = 1                          
        // temp = 0
        // 
        // TREE:
        //          6'
        //         / \ 
        //        5'  0' 
        //       /  
        //      2'                        count = 1     
        //         
        // stack [6', 0', 0', 0', 0', 0'  count = 2
        // temp = 3'
        // 
        //  PART TREE:
        //          3'
        //         /
        //        0'                       count = 1
        //    
        // TREE:
        //          6'
        //         / \ 
        //        5'  3' 
        //       /   /
        //      2'  0'
        //     
        // stack [6', 3', 0', 0', 0', 0'   count = 2
        // temp = 1'
        // 
        // TREE:
        //          6'
        //         / \ 
        //        5'  3' 
        //       /   / \ 
        //      2'  0'  1'
        // 
        int count = 0;
        for (int i = 0; i < len; i++) {
            TreeNode temp = new TreeNode(A[i]);
            while (count > 0 && A[i] > stack[count - 1].val) {
                temp.left = stack[count - 1];
                count--;
            }
            if (count > 0) {
                stack[count - 1].right = temp;
            }

            stack[count++] = temp;
        }
        
        return stack[0]; /* Largest Node */
    }
}



///////////////////
// Using a Stack //
///////////////////

public class Solution {
    /* Prevent stack overflow as well as store the temp value */ 
    public TreeNode constructMaximumBinaryTree(int[] A) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = null;
        for (int i = 0; i <= A.length; i++) {
            TreeNode right = i == A.length ? new TreeNode(Integer.MAX_VALUE) :
                                             new TreeNode(A[i]);
            while (!stack.isEmpty()) {
                if (right.val > stack.peek().val) {
                    TreeNode nodeNow = stack.pop();
                    if (stack.isEmpty()) {
                        right.left = nodeNow;
                    } else {
                        TreeNode left = stack.peek();
                        if (left.val > right.val) {
                            right.left = nodeNow;
                        } else {
                            left.right = nodeNow;
                        }
                    }
                } else {
                    break;
                }
            }
            stack.push(right);
        }
        return stack.peek().left;
    }
}

