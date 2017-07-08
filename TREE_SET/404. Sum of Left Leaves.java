/**
 * Find the sum of all left leaves in a given binary tree.
 * -- LeetCode 404
 */

/*
    Example:

        3
       / \
      9  20
        /  \
       15   7

    There are two left leaves in the binary tree, with values 9 and 15 
    respectively. Return 24.
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

/////////////////////////////
// Solution #0 Recursive_1 //
/////////////////////////////

public class Solution {

    /* Using a variable to control if it's leaf */
    int leftSum = 0;
    /* Make sure it's leaf */
    int level = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        helper(root);
        return leftSum;
    }

    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && level != 0) {
            leftSum += root.val;
            return;
        }
     
        if (root.left != null) {
            level++;
            helper(root.left);
        }

        level = 0;
        if (root.right != null) {
            helper(root.right);
        }

    }
}

/////////////////////////////
// Solution #1 Recursive 2 //
/////////////////////////////

public class Solution {
    /*
        For given node we check whether its left child is a leaf. If it is the 
        case, we add its value to answer, otherwise recursively call method on 
        left child. For right child we call method only if it has at least one 
        non-null child.
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;

        int leftSum = 0;

        /* Core part */
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) {
                leftSum += root.left.val;
            } else {
                leftSum += sumOfLeftLeaves(root.left);
            }
        }

        leftSum += sumOfLeftLeaves(root.right);
        return leftSum;
    }    
}

///////////////////////////////
// Solution #2 BFS traversal //
///////////////////////////////

public class Solution {
    /* Core part is the same as the second solution */ 
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
          //   3
          //  / \
          // 9  20
          //   /  \
          //  15   7
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        TreeNode cur = root;
        q.offer(cur);
        int sumLeft = 0;
        
        while (!q.isEmpty()) {
            cur = q.poll();
            
            /* Solve the problem */
            if (cur.left != null) {
                if (cur.left.left == null && cur.left.right == null) {
                    sumLeft += cur.left.val;
                }
            }
            
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }

        return sumLeft;
    }     
}


