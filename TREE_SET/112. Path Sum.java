/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf
 * path such that adding up all the values along the path equals the given sum.
 * -- LeeCode 112
 * -- TreeNode 24.1
 */

/*
    For example:
    Given the below binary tree and sum = 22,
                  5
                 / \
                4   8
               /   / \
              11  13  4
             /  \      \
            7    2      1
    Return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */

public class Solution {

    /*
        The basic idea is to subtract the value of current node from sum until it reaches a leaf node and the subtraction equals 0, then we know that we got a hit. Otherwise the subtraction at the end could not be 0.
     */    
    public boolean hasPathSum(TreeNode root, int sum) {
        /* Edge Case */
        if (root == null) {
            return false;
        }
        
        /* Only true situation */
        int newSum = sum - root.val;
        if (newSum == 0 && root.left == null && root.right == null) {
            return true;
        }
        
        /* Go deeper */
        boolean left  = hasPathSum(root.left, newSum);
        boolean right = hasPathSum(root.right, newSum);
        
        /* See if one of the two sides is true */
        return left || right;
        
    }
}