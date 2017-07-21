/**
 * Given a binary tree, find the length of the longest consecutive sequence
 * path.
 */

/*
    The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

    For example,
       1
        \
         3
        / \
       2   4
            \
             5
    Longest consecutive sequence path is 3-4-5, so return 3.
       2
        \
         3
        / 
       2    
      / 
     1
    Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
 */
 
//////////////////
// Preorder DFS //
//////////////////

public class Solution {
    /* Important problem */
    public int longestConsecutive(TreeNode root) {
        return longestConsecutiveHelper(root, null, 0);
    }
    private int longestConsecutiveHelper(TreeNode root, 
                                         TreeNode parent, 
                                         int length) {
        if (root == null) {
            return length;
        }

        if (parent != null && root.val == parent.val + 1) {
            length++;
        } else {
            length = 1;
        }

        int left  = longestConsecutiveHelper(root.left, root, length);
        int right = longestConsecutiveHelper(root.right, root, length);

        return Math.max(length, Math.max(left, right));

    }

}

///////////////////
// PostOrder DFS //
///////////////////

public class Solution {
    private int maxL = 0;
    public int longestConsecutive(TreeNode root) {
        postOrderDFS(root);
        return maxL;
    }
    private int postOrderDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        /* Add the 1 beforehand assuming they are consecutive */
        int left = postOrderDFS(root.left) + 1;
        int right = postOrderDFS(root.right) + 1;

        /* Where we set the length to 1 again */
        if (root.left != null && root.val + 1 != root.left.val) {
            left = 1;
        }
        if (root.right != null && root.val + 1 != root.right.val) {
            right = 1;
        }  

        int tempL = Math.max(left, right);
        maxL = Math.max(maxL, tempL);
        return tempL;      
    }
}

