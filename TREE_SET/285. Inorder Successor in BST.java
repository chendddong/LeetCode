/*
    Given a binary search tree and a node in it, find the in-order successor of
    that node in the BST.

    Note: If the given node has no in-order successor in the tree, return null.
    -- LintCode 448
    -- LeetCode 285
    -- TreeDemo 
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

////////////////////////////////
// Straight forward traversal //
////////////////////////////////

class ResultType {
    public boolean found;
    public int target;
    public ResultType(boolean found, int target) {
        this.found = found;
        this.target = target;
    }
}
public class Solution {
    /* for the final answer */
    TreeNode targetNode = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        /* Check the edge */
        if (root == null || p == null) {
            return null;
        }
        /* Get the traversal list */
        ArrayList<Integer> list = getList(root);

        /* Find the target we are looking for */ 
        ResultType targetRT = findTarget(p.val, list);

        /* Get that node we need */
        if (targetRT.found) {
            findNode(root, targetRT.target);
            return targetNode;
        } 
        
        return null;
        
    }
    private ArrayList<Integer> getList(TreeNode root) { 
        ArrayList<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        ArrayList<Integer> left = getList(root.left);
        ArrayList<Integer> right =  getList(root.right);

        list.addAll(left);
        list.add(root.val);
        list.addAll(right);

        return list;
    }
    private ResultType findTarget(int p, ArrayList<Integer> list) {
        int size = list.size();

        for (int element : list) {
            if (element == p) {
                break;
            }
            size--;
        }
        // last position
        if (size <= 1) {
            return new ResultType(false, 0);
        }

        return new ResultType(true, list.get(list.size() - size + 1));        
    }
    private void findNode(TreeNode root, int targetValue) {
        if (root == null) {
            return;
        }

        if (root.val == targetValue) {
            targetNode = root;
            return;
        }

        findNode(root.left, targetValue);
        findNode(root.right, targetValue);
    }
}

///////////////////////
// Inorder Traversal //
///////////////////////

public class Solution {

    /*
        Inorder traversal.
        If the pre is the p then just return the cur.
     */   
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        ArrayDeque<TreeNode> s = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;

        while (!s.isEmpty() || cur != null) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            cur = s.peek();
            s.pop();

            /* Solve the problem */
            if (pre == p) {
                return cur;
            }

            pre = cur;
            cur = cur.right;
        }

        return null;

    }
}

///////////////////
// Binary search //
///////////////////

public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        /* !!!Important. Record the successor along the way */
        TreeNode successor = null;


        //       100
        //     /    \
        //    40     180
        //  /  \     /
        // 30   60  110

        /* Find p */
        while (root != null && root != p) {
            /* Only update successor when going left */
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        /* p is null */
        if (root == null) {
            return null;
        }
        /* p's right is null */
        if (root.right == null) {
            return successor;
        }
        
        /* Go right */
        root = root.right;
        /* Go deep to the far left*/
        while (root.left != null) {
            root = root.left;
        }
        
        return root;
    }
}


///////////////////////////////
// Successor Simple Solution //
///////////////////////////////

public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
      if (root == null)
        return null;

      if (root.val <= p.val) {
        return inorderSuccessor(root.right, p);
      } else {
        TreeNode left = inorderSuccessor(root.left, p);
        return (left != null) ? left : root;
      }
    }    
}


/////////////////
// Predecessor //
/////////////////

public class Solution {
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
      if (root == null)
        return null;

      if (root.val >= p.val) {
        return inorderPredecessor(root.left, p);
      } else {
        TreeNode right = inorderPredecessor(root.right, p);
        return (right != null) ? right : root;
      }
    }    
}



