/**
 * Given a binary tree, determine if it is height-balanced.
 * -- LeetCode 110
 * -- TreeNode 9
 */

/*
    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */

//////////////////////////
// Recursion + getDepth //
//////////////////////////

public class Solution {
    /*
        (1) The height difference between left and right children should be < 1.
        (2) The left children and right children have to be all AVL.
     */    
    public boolean isBalanced(TreeNode root) {
        /* Edge is true */        
        if (root == null) {
            return true;
        }

        /* Must all be balanced */        
        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }
        
        /* Get the depth difference */        
        int diff = Math.abs(getDepthRec(root.left) - getDepthRec(root.right));
        if (diff > 1) {
            return false;
        }
        
        return true;
    }

    private static int getDepthRec(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        return Math.max(getDepthRec(root.left), getDepthRec(root.right)) + 1;
    }
}

////////////////////
// Use ResultType //
////////////////////

public class Solution {

    private static class ResultType {
        boolean isBalanced;
        int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        return helper(root).isBalanced;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, -1);
        }

        /* Divide */
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        /* subtree not balance */
        /* see if the left and the right are balanced */
        if (!left.isBalanced || !right.isBalanced) {
            return new ResultType(false, -1);
        }

        /* root not balance */
        /* we can use 0 for the maxDepth */
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
            return new ResultType(false, -1);
        }

        return new ResultType(true, Math.max(left.maxDepth, right.maxDepth) + 1);
    }
}