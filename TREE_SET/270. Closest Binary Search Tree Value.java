/**
 * Given a non-empty binary search tree and a target value, find the value in
 * the BST that is closest to the target.
 */

/*
    Note:
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.
 */

/*
              100
            /    \
           40     180       140
         /  \     /
        30   60  110

 */

///////////////
// Traversal //
///////////////

public class Solution {
    /* Time: O(n) */
    /* Use inorder traversal; Use a two variables to store some temp values */
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        ArrayDeque<TreeNode> s = new ArrayDeque<>();
        TreeNode cur = root;
        double MAX = Double.MAX_VALUE;
        int result = 0;
        while (cur != null || !s.isEmpty()) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            cur = s.peek();
            s.pop();

            /* Solve the problem; Always update two when concerning the MAX */
            if (Math.abs((double) cur.val - target) < MAX) {
                result = cur.val;
                MAX = Math.abs((double) cur.val - target);
            }
            
            cur = cur.right;
        }
        return result;
    }
}

///////////////
// Recursive //
///////////////

public class Solution {
    /* 
        Interesting recursive method and it reduce the runtime if the tree is
        extremely large and the answer is just near the root 
     */
    public int closestValue(TreeNode root, double target) {
        int a = root.val;

        TreeNode kid = null;
        /* Go left */
        if (target < a) {
            kid = root.left;
        /* Go right */
        } else {
            kid = root.right;
        }
        /* Halt point */
        if (kid == null) {
            return a;
        }
        
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}

