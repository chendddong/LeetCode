/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of
 * two given nodes in the BST.
 * -- LeetCode 235
 * -- TreeDemo 11.3
 */

/*
    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

            _______6______
           /              \
        ___2__          ___8__
       /      \        /      \
       0      _4       7       9
             /  \
             3   5
    For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */

public class Solution {
    /*
        We could also use 2) LCARec to solve this since BST's also BT. And this
        algorithm could be log(n) if it is an AVL. The runtime of LCARec is O(n).
     */    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /* Null work */
        if (root == null || p == null || q == null) {
            return null;
        }

        /* Root work */
        if (root == p || root == q) {
            return root;
        }

        /* Got the min and max and compare */
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);

        /* If root val is larger than max, Go left */
        if (root.val > max) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < min) {
        /* If root val is smaller than min, Go right */
            return lowestCommonAncestor(root.right, p, q);
        }

        /* if root is in the middle, just return the root */
        return root;
    }
}