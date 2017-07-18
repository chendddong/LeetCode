/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * -- LeetCode 94
 * -- TreeNode 3.3, 3.4
 */

/*
    For example:
    Given binary tree [1,null,2,3],
       1
        \
         2
        /
       3
    return [1,3,2].

    Note: Recursive solution is trivial, could you do it iteratively?
 */

///////////////
// Recursive //
///////////////

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);

        result.addAll(left);
        result.add(root.val);
        result.addAll(right);

        return result;
    }
}

///////////////
// Iteration //
///////////////

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayDeque<TreeNode> s = new ArrayDeque<>();
        TreeNode cur = root;

        while (cur != null || !s.isEmpty()) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            cur = s.peek();
            s.pop();
            result.add(cur.val);
            cur = cur.right;
        }

        return result;
    }
}
