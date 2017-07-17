/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 */

/*
    Example 1:
    Input:

        2
       / \
      1   3

    Output:
    1

    Example 2: 
    Input:

            1
           / \
          2   3
         /   / \
        4   5   6
           /
          7

    Output:
    7
    Note: You may assume the tree (i.e., the given root node) is not NULL.
 */

///////////////////////
// BFS RIGHT TO LEFT //
///////////////////////

public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        TreeNode lastNode = null;
        while (!q.isEmpty()) {
            lastNode = q.poll();
            if (lastNode.right != null) {
                q.offer(lastNode.right);
            }
            if (lastNode.left != null) {
                q.offer(lastNode.left);
            }
        }

        return lastNode.val;
    }   
}

///////////////
// Recursion //
///////////////

public class Solution {
    /* Pass the level to the helper and Update the answer in the recursion */
    public int findBottomLeftValue(TreeNode root) {
        helper(root,0);
        return val;
    }
    private int maxLevel = Integer.MIN_VALUE;
    private int val = 0;
    private void helper(TreeNode root, int level){
        /* Base */
        if(root == null) {
            return;
        }

        /* Update the answer */
        if(level > maxLevel){
            val = root.val;
            maxLevel = level;
        }

        /* When going down, we have to increment the level */
        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }
}


 