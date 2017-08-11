/**
 * Given a binary tree where all the right nodes are either leaf nodes with a
 * sibling (a left node that shares the same parent node) or empty, flip it
 * upside down and turn it into a tree where the original right nodes turned
 * into left leaf nodes. Return the new root.
 */

/*
    For example:
    Given a binary tree {1,2,3,4,5},
        1
       / \
      2   3
     / \
    4   5
    return the root of the binary tree [4,5,2,#,#,3,1].
       4
      / \
     5   2
        / \
       3   1  
    confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/

///////////////
// Recursive //
///////////////

public class Solution {
    /*
        We can think the new tree as follows:
        Given a binary tree {1,2,3,4,5},
            1                1    
           / \              /  
          2   3  <-------> 2 -- 3
         / \              / 
        4   5            4 -- 5
                    newRoot is 4
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        /* The connection is the tricky part; Think this through */
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return newRoot;
    }
}
 
///////////////
// Iterative //
///////////////

public class Solution {
    /*
        We can think the new tree as follows:
        Given a binary tree {1,2,3,4,5},
            1                1    
           / \              /  
          2   3  <-------> 2 -- 3
         / \              / 
        4   5            4 -- 5
                    newRoot is 4
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode prev = null;

        while (cur != null) {
            next = cur.left;

            /* Swap */
            cur.left = temp
            temp = cur.right;
            cur.right = prev;

            prev = cur;
            cur = next;
        }

        return prev;
    }
}