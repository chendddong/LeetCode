/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
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
 * 
 */

/*
    Example:
    preorder = {1, 2,4,5, 3,6}
    inorder  = {4,2,5, 1, 3,6}
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /* Edge */
        if (preorder == null || preorder.length == 0 ||
            inorder  == null || inorder.length  == 0) {
            return null;
        }
        /* Create root and find root' index */
        TreeNode root = new TreeNode(preorder[0]);
        int rootIndex = findIndex(preorder[0], inorder);

        /*
            How to get subArray:
            int[] newArray = Arrays.copyOfRange(oldArray, startIndex, endIndex);
         */
        
        /* Divide */
        TreeNode left = buildTree(Arrays.copyOfRange(preorder, 1, rootIndex + 1),
                                  Arrays.copyOfRange(inorder, 0, rootIndex));
        TreeNode right = buildTree(Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length),
                                   Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length));        
        /* Connecting */
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

/////////////////////////////////////////////////////
// Use a Helper pass index value Reduce space cost //
/////////////////////////////////////////////////////

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(0, 0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode buildTreeHelper(int preStart, int inStart,
        int inEnd, int[] preorder, int[] inorder) {

        /* Base: Test the index */
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        /* Create root */
        TreeNode root = new TreeNode(preorder[preStart]);

        int inIndex = 0; /* Index of the current root in inorder */
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }

        /* Recursion; Be careful of the Indices */
        root.left = buildTreeHelper(preStart + 1, inStart, inIndex - 1,
            preorder, inorder);
        root.right = buildTreeHelper(preStart + inIndex - inStart + 1, inIndex
            + 1, inEnd, preorder, inorder);

        return root;
    }
}





