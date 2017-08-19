/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given
 * nodes in the tree.
 */

/*
    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

            _______3______
           /              \
        ___5__          ___1__
       /      \        /      \
       6      _2       0       8
             /  \
             7   4
    For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
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

////////////////
// Recursive  //
////////////////

class Solution {
    /*
        (1) null situation
        (2) root situation
        (3) left null and right null situation
     */    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1,TreeNode
       node2) {
        
        /* (1) */
        if (root == null || node1 == null || node2 == null) {
            return null;
        }

        /* (2) */
        if (root == node1 || root == node2) {
            return root;
        }

        /* (3) */
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);

        /* If didn't find LCA the left tree, then just return it from right. */
        if (left == null) {
            return right;
        /* If didn't find LCA the right tree, then just return it from left. */
        } else if (right == null) {
            return left;
        }

        /* If we found LCA from both sides, just return the root */
        return root;
               
    }
}

/////////////////////////////////////
// StraightForward record the path //
/////////////////////////////////////

class Solution {
    /* Record the path along the way and compare them */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode r1, TreeNode r2) {
        /* Edge case */
        if (root == null || r1 == null || r2 == null) {
            return null;
        }

        /* Initialize two dynamic arrays */
        ArrayList<TreeNode> list1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> list2 = new ArrayList<TreeNode>();

        /* See if we can find the path */
        boolean find1 = LCAPath(root, r1, list1);
        boolean find2 = LCAPath(root, r2, list2);

        /* If didn't find any of the node, just return a null. */
        if (!find1 || !find2) {
            return null;
        }

        /* Note that it would boost the performance if we use Iterator. */
        Iterator<TreeNode> iter1 = list1.iterator();
        Iterator<TreeNode> iter2 = list2.iterator();

        /* Iterate and compare the node */
        TreeNode last = null;
        while (iter1.hasNext() && iter2.hasNext()) {
            TreeNode tmp1 = iter1.next();
            TreeNode tmp2 = iter2.next();

            if (tmp1 != tmp2) {
                return last;
            }

            last = tmp1;
        }
        /*
            If we cannot find any node which is different, which means Node 1
            and Node 2 are the same node. So just return the last one.
         */
        return last;
    }
    public static boolean LCAPath(TreeNode root,
                                  TreeNode node,
                                  ArrayList<TreeNode> path) {
        /* if can't find the node in the tree, we should return a empty path. */
        if (root == null || node == null) {
            return false;
        }

        /* Add the root node */
        path.add(root);

        if (root != node
                && !LCAPath(root.left, node, path)
                && !LCAPath(root.right, node, path)
                ) {
            /*
                Didn't find the node. We should remove the node added before.
                AKA backtracking
            */
            path.remove(root);
            return false;
        }

        /* Found the node */
        return true;
    }
}