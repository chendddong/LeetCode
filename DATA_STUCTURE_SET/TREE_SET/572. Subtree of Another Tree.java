/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly
 * the same structure and node values with a subtree of s. A subtree of s is a
 * tree consists of a node in s and all of this node's descendants. The tree s
 * could also be considered as a subtree of itself.
 * -- LeetCode 572
 * -- LintCode 245
 */

/*
    Example 1:
    Given tree s:

         3
        / \
       4   5
      / \
     1   2
    Given tree t:
       4 
      / \
     1   2
    Return true, because t has the same structure and node values with a subtree of s.
    Example 2:
    Given tree s:

         3
        / \
       4   5
      / \
     1   2
        /
       0 
    Given tree t:
       4
      / \
     1   2
    Return false.
 */

public class Solution {
    /* Use Recursion */
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        /* Edge cases */
        if (s == null) {
            return false;
        }
        if (t == null) {
            return true;
        }

        return helper(s, t);
    }

    /* Recursively traverse node s */
    private static boolean helper(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }

        if (isSameRec(s, t)) {
            return true;
        } 

        return helper(s.left, t) || helper(s.right, t);
    }

    /* 
        To see if two trees are the same 
        -- TreeDemo 8 
        -- LintCode 469
        -- LeetCode 100 
     */
    private static boolean isSameRec(TreeNode r1, TreeNode r2) {
        /* (1) */
        if (r1 == null && r2 == null) {
            return true;
        }

        /* (2) */
        if (r1 == null || r2 == null) {
            return false;
        }

        /* (3) */
        return r1.val == r2.val &&
                isSameRec(r1.left, r2.left) && isSameRec(r1.right, r2.right);
    }    
}




