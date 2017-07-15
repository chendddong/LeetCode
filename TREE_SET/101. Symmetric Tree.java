/**
 * Given a binary tree, check whether it is a mirror of itself (ie,
 * symmetric around its center).
 */

/*
    For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

        1
       / \
      2   2
     / \ / \
    3  4 4  3
    But the following [1,2,2,null,3,null,3] is not:
        1
       / \
      2   2
       \   \
       3    3
    Note:
    Bonus points if you could solve it both recursively and iteratively.

 */

////////////////////////////////
// Got the Mirror and compare //
////////////////////////////////

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        /* Get the mirror tree */
        TreeNode rootMirror = mirrorCopy(root);
        /* See if the mirror is the same as the original tree */ 
        return isSameTree(root, rootMirror);
    }
    private TreeNode mirrorCopy(TreeNode root) {
        if (root == null) {
            return null;
        }        
        TreeNode rootCopy = new TreeNode(root.val);
        rootCopy.left = mirrorCopy(root.right);
        rootCopy.right = mirrorCopy(root.left);
        return rootCopy;
    }
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) 
                              && isSameTree(p.right, q.right);
    }
}

////////////////////////////////
// Compare the root with root //
////////////////////////////////

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        /* See if the root are mirrors */
        return isMirrorRec(root, root);
    }
    /*
        (1) if two tree are all empty return true.
        (2) if one of those is empty and the other is not, return false
        (3) if two trees are both not empty if their val and their children are
        all the same to the opposite branch return true, else return false;
    */
    /* Similar to isSameRec except altering the left and right children */
    public static boolean isMirrorRec(TreeNode r1, TreeNode r2){
        /* (1) */
        if (r1 == null && r2 == null) {
            return true;
        }
        /* (2) */
        if (r1 == null || r2 == null) {
            return false;
        }
        /* (3) */
        return r1.val == r2.val
                && isMirrorRec(r1.left, r2.right)
                && isMirrorRec(r1.right, r2.left);
    }    
}

////////////////
// Iteration  //
////////////////

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        /* See if the root are mirrors */
        return isMirror(root, root);
    }
    /*
        (1) if two tree are all empty return true.
        (2) if one of those is empty and the other is not, return false
        (3) compare tree_1's left children with tree_2's right children and vise
        versa, they can be:
            i.      both null (return true at the end)
            ii.     both be not null (go deeper)
            iii.    one of the children is null and the other is not
     */
    public static boolean isMirror(TreeNode r1, TreeNode r2){
        /* (1) */
        if (r1 == null && r2 == null) {
            return true;
        }

        /* (2) */
        if (r1 == null || r2 == null) {
            return false;
        }

        /* Use two stacks */
        ArrayDeque<TreeNode> s1 = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> s2 = new ArrayDeque<TreeNode>();

        s1.push(r1);
        s2.push(r2);

        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode cur1 = s1.pop();
            TreeNode cur2 = s2.pop();

            /* Root */
            if (cur1.val != cur2.val) {
                return false;
            }

            /* Compare tree_1's left children with tree_2's right children */
            TreeNode left1 = cur1.left;
            TreeNode right1 = cur1.right;
            TreeNode left2 = cur2.left;
            TreeNode right2 = cur2.right;

            if (left1 != null && right2 != null) {
                s1.push(left1);
                s2.push(right2);
            } else if (!(left1 == null && right2 == null)) {
                return false;
            }

            /* Compare tree_2's left children with tree_1's right children */
            if (right1 != null && left2 != null) {
                s1.push(right1);
                s2.push(left2);
            } else if (!(right1 == null && left2 == null)) {
                return false;
            }
        }

        return true;
    }   
}

