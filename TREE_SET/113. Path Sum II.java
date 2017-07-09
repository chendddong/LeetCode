/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each
 * path's sum equals the given sum.
 * -- LeetCode 113
 * -- LintCode 376
 */

/*
  For example:
  Given the below binary tree and sum = 22,
                5
               / \
              4   8
             /   / \
            11  13  4
           /  \    / \
          7    2  5   1
  return
  [
     [5,4,11,2],
     [5,8,4,5]
  ]
 */


 

public class Solution {

    /* 
      Since we need the total sum from root to leaf, it's could not be more
      obvious that it's DFS
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        path.add(root.val);

        /* We need both single path the final result */
        pathSumHelper(root, root.val, sum, path, result);
        return result;
    }

    private void pathSumHelper(TreeNode root, 
                               int sum, 
                               int target, 
                               List<Integer> path, 
                               List<List<Integer>> result) {

        /* Base case; Down to the leaf node and solve the problem */
        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path)); /* Deep copy */
            }
            return;
        }

        /* Go left */
        if (root.left != null) {
            path.add(root.left.val);
            pathSumHelper(root.left, sum + root.left.val, target, path, result);
            path.remove(path.size() - 1); /* Backtracking */
        }

        /* Go right (identical to the left side )*/
        if (root.right != null) {
            path.add(root.right.val);
            pathSumHelper(root.right, sum + root.right.val, target, path, result);
            path.remove(path.size() - 1); /* Backtracking */
        }        

    }
}
