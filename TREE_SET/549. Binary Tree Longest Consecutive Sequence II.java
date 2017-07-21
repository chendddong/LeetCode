/**
 * Given a binary tree, you need to find the length of Longest Consecutive Path
 * in Binary Tree.
 * -- Leet 549
 * -- Lint 614
 * -- TreeDemo 15.3.*
 */

/*
    Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

    Example 1:
    Input:
            1
           / \
          2   3
    Output: 2
    Explanation: The longest consecutive path is [1, 2] or [2, 1].
    Example 2:
    Input:
            2
           / \
          1   3
    Output: 3
    Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
    Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */

class ResultType {
    public int max_length;
    public int max_down;
    public int max_up;
    ResultType(int l, int d, int u) {
        max_length = l;
        max_down = d;
        max_up = u;
    }
}
public class Solution {
    public int longestConsecutive(TreeNode root) {
        return longestConsecutiveIIHelper(root).max_length;
    }

    private ResultType longestConsecutiveIIHelper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        /* bottom up */
        ResultType left = longestConsecutiveIIHelper(root.left);
        ResultType right = longestConsecutiveIIHelper(root.right);

        int down = 0, up = 0;

        /* Four situations */
          //   2
          //  / 
          // 1           

        if (root.left != null && root.left.val + 1 == root.val)
            down = Math.max(down, left.max_down + 1);

          //   1
          //  / 
          // 2

        if (root.left != null && root.left.val - 1 == root.val)
            up = Math.max(up, left.max_up + 1);

          //   2
          //    \
          //     1

        if (root.right != null && root.right.val + 1 == root.val)
            down = Math.max(down, right.max_down + 1);

          //   1
          //    \
          //     2
        
        if (root.right != null && root.right.val - 1 == root.val)
            up = Math.max(up, right.max_up + 1);

        /* Solve the problem */ 
        int len = down + 1 + up;
        len = Math.max(len, Math.max(left.max_length, right.max_length));

        return new ResultType(len, down, up);
    }

}

