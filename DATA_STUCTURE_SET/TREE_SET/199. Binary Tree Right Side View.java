/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 */

/*
    For example:
    Given the following binary tree,
       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---
       \
        7
    You should return [1, 3, 4].
 */

/////////
// BFS //
/////////

public class Solution {
    /* Use BFS right from left add the first element */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }    

        /* BFS */
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                /* Use node */
                TreeNode node = q.poll();
                if (level == result.size()) {
                    result.add(node.val);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
            }
            level++;
        }

        return result;
    }
}

/////////
// DFS //
/////////

public class Solution {
    /* DFS top to bottom compare the size of the result and the level */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightSideViewHepler(root, result, 0);
        return result;
    }

    private void rightSideViewHepler(TreeNode root, 
                                     List<Integer> result, 
                                     int level) {
        if (root == null) {
            return;
        }

        if (level == result.size()) {
            result.add(root.val);
        }        

        rightSideViewHepler(root.right, result, level + 1);
        rightSideViewHepler(root.left,  result, level + 1);
    }
}


