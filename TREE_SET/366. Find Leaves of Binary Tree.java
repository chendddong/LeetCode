/**
 * Given a binary tree, collect a tree's nodes as if you were doing this:
 * Collect and remove all leaves, repeat until the tree is empty.
 */

/*
    Example:

    Given binary tree 
              1
             / \
            2   3
           / \     
          4   5    
    Returns [4, 5, 3], [2], [1].

    Explanation:
    1. Removing the leaves [4, 5, 3] would result in this tree:

              1
             / 
            2          
    2. Now removing the leaf [2] would result in this tree:

              1          
    3. Now removing the leaf [1] would result in the empty tree:

              []         
    Returns [4, 5, 3], [2], [1].

 */

//////////////////////////////////////
// Smartly Use the Height of a node //
//////////////////////////////////////

public class Solution {
    /*
        For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is:

        The height of a node is the number of edges from the node to the deepest leaf. --CMU 15-121 Binary Trees

        I used a helper function to return the height of current node. According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)).

        The height of a node is also the its index in the result list (result). For example, leaves, whose heights are 0, are stored in result[0]. Once we find the height of a node, we can put it directly into the result.      
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        height(root, result);
        return result;
    }
    private int height(TreeNode node, List<List<Integer>> result){
        if(null == node)  {
            return -1;
        }

        /* 
            Bottom-up; Go deeper to the tree and solve the problem while coming
            back.

            Use the height of the node and associate it with the size of the
            result.
         */
        int level = 1 + Math.max(height(node.left, result), 
                                 height(node.right, result));

        /* A MUST KNOW TECHNIQUE -- Brilliant as hell */
        if(result.size() < level + 1) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);
        
        /* 
            'Delete' the node; We can omit this since it won't affect the answer.
         */
        node.left = null;
        node.right = null;

        return level;
    }
}


////////////////////////////////////
// Straightforward isLeave or not //
////////////////////////////////////


public class Solution {
    /* 
        Solving the problem by using the isLeave function; 
        Add leaves while deleting the tree!

     */
    public List<List<Integer>> findLeaves(TreeNode root) {

        List<List<Integer>> leaveslist = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();

        while (root != null) {
            /* Handle the root */
            if (isLeave(root, leaves)) {
                root = null;
            }
            leaveslist.add(leaves);
            leaves = new ArrayList<Integer>();
        }

        return leaveslist;
    }
    public boolean isLeave(TreeNode node, List<Integer> leaves) {

        /* Leaf */
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return true;
        }

        /* Add leaves while deleting the tree */

        /* Go deeper to left */ 
        if (node.left != null) {
            if (isLeave(node.left, leaves)) {
                node.left = null;
            }
        }

        /* Go deeper to right */         
        if (node.right != null) {
            if (isLeave(node.right, leaves)) {
                node.right = null;
            }
        }

        return false;
    }
}


