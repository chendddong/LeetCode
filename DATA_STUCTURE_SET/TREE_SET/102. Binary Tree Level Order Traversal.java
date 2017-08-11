/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * -- LeetCode 102, 107 
 * -- LintCode 69, 70
 * -- TreeDemo 4
 */

/*
    Example
    Given binary tree {3,9,20,#,#,15,7},

        3
       / \
      9  20
        /  \
       15   7
     

    return its level order traversal as:

    [
      [3],
      [9,20],
      [15,7]
    ]
 */

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {

    /* Classic  BFS */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        ArrayDeque<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            results.add(level);

        }
        
        return results;
    }
}
