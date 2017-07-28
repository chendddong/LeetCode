/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * -- LeetCode 105, 106
 * -- TreeDemo 13.1
 */

/*
    Note:
    You may assume that duplicates do not exist in the tree.
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
    Example:
    postorder = {4,5,2, 6,3, 1}    
    inorder   = {4,2,5, 1, 3,6}


              1
            /   \
           2     3
         /  \     \
        4    5     6
 */

////////////////////////////////////////
// StraightForward Arrays.copyOfRange //
////////////////////////////////////////

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /* Edge */
        if (postorder == null || postorder.length == 0 ||
            inorder  == null || inorder.length  == 0) {
            return null;
        }

        /* Create root and find root' index */
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int rootIndex = findIndex(postorder[postorder.length - 1], inorder);        
        /* Divide */
        TreeNode left = buildTree(Arrays.copyOfRange(inorder, 0,
           rootIndex), Arrays.copyOfRange(postorder, 0, rootIndex));

        TreeNode right = buildTree(Arrays.copyOfRange(inorder, rootIndex + 1,
           inorder.length), Arrays.copyOfRange(postorder, rootIndex,
           postorder.length - 1));  

        root.left = left;
        root.right = right;

        return root;        
    }
    private int findIndex(int num, int[] inorder) {
        int index = 0;
        for (int e : inorder) {
            if (inorder[index] == num) {
                break;
            }
            index++;
        }
        return index;
    }    
}

