/**
 * Given a binary tree, count the number of uni-value subtrees.
 * -- TreeDemo 23.2
 * -- LeetCode 250
 */

/*
    A Uni-value subtree means all nodes of the subtree have the same value.

    For example:
    Given binary tree,
                  5
                 / \
                1   5
               / \   \
              5   5   5
    return 4.
 */

/////////////////
// Recursion 1 //
/////////////////

public class Solution {
    int res = 0;
    public int countUnivalSubtrees(TreeNode root) {
        isUnival(root, 0);
        return res;
    }
    private boolean isUnival(TreeNode root, int val) {
        if (root == null) {
            return true;
        }

        /* Go deeper then deal with the root; Use the bit OR!!! */
        if (!isUnival(root.left, root.val) 
         | !isUnival(root.right, root.val)) {
            return false;
        }

        res++;

        return root.val == val;

    }

}

/////////////////
// Recursion 2 //
/////////////////

          //     5
          //    / \
          //   1   5
          //  / \   \
          // 5   5   5

public class Solution {
    /* 
        Note: All parameters are passed by value. So we can not pass
        integer to the function and hope to return the value we want. As an
        alteration, we can use a single-element array, and the return it.
     */
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1];
        countUnivalSubtreesHelper(root, count);
        return count[0];
    }
    private boolean countUnivalSubtreesHelper(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }

        boolean left = countUnivalSubtreesHelper(node.left, count);
        boolean right = countUnivalSubtreesHelper(node.right, count);

        /* Basic logic; Go over the example above */
        if (left && right) {
            if (node.left != null && node.left.val != node.val) {
                return false;
            }
            if (node.right != null && node.right.val != node.val) {
                return false;
            }
            count[0]++;
            return true;
        }

        return false;

    }


}

/*
    So, never pass a primitive type of variables to another function and return
    the value then and it will not change since Java only pass the copy of the
    value not the actual reference.
 */