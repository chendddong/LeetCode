/**
 * Given a binary tree, find its minimum depth.
 * -- LeetCode 111
 * -- TreeDemo 2.1.1
 */

/*
    The minimum depth is the number of nodes along the shortest path from the
    root node down to the nearest leaf node.
 */

public class Solution {

    /* 
        Pay attention to the definition of minimum depth; The 1 we are adding
        is the root node itself. 
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }  
        //      1
        //       \
        //        3
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }         
        //      1
        //     /
        //    2    
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        //      1
        //     / \
        //    2   3       
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}