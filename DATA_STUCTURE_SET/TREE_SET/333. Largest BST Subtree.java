/*
    Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 */

/*
    Note:
    A subtree must include all of its descendants.
    Here's an example:
        10
        / \
       5  15
      / \   \ 
     1   8   7
    The Largest BST Subtree in this case is the highlighted one. 
    The return value is the subtree's size, which is 3.
    Follow up:
    Can you figure out ways to solve it with O(n) time complexity?
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
/*
    Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 */

/*
    Note:
    A subtree must include all of its descendants.
    Here's an example:
        10
        / \
       5  15
      / \   \ 
     1   8   7
    The Largest BST Subtree in this case is the highlighted one. 
    The return value is the subtree's size, which is 3.
    Follow up:
    Can you figure out ways to solve it with O(n) time complexity?
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

///////////////
// Recursive //             AC
///////////////

class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (isValid(root, null, null)) return countNode(root);
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    public boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && min >= root.val) return false;
        if (max != null && max <= root.val) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    public int countNode(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + countNode(root.left) + countNode(root.right);
    }    
}

////////////////
// ResultType //     More intuitive !
////////////////

public class Solution {
    /* size of current tree, range of current tree [rangeLower, rangeUpper] */
    class ResultType { 
        int size;
        int lower;
        int upper;
        
        ResultType(int size, int lower, int upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }
    
    int max = 0;
    
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) { return 0; }    
        traverse(root);
        return max;
    }
    
    private ResultType traverse(TreeNode root) {
        /* For easy comparison, we need to set lower & upper to max and min */
        if (root == null) return new ResultType(0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        ResultType left = traverse(root.left);
        ResultType right = traverse(root.right);
        /* Not BST */
        if (left.size == -1 || right.size == -1 || root.val <= left.upper ||
            root.val >= right.lower) {
            return new ResultType(-1, 0, 0);
        }
        int size = left.size + 1 + right.size;
        max = Math.max(size, max); /* Update the max */
        return new ResultType(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
}


