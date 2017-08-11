/**
 * You need to find the largest value in each row of a binary tree.
 */

/*
    Example:
    Input: 

              1
             / \
            3   2
           / \   \  
          5   3   9 

    Output: [1, 3, 9]
 */

/////////
// BFS //
/////////

public class Solution {
    /* Straightforward BFS */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (node.val > max) {
                    max = node.val;
                }
            }
            result.add(max);
        }

        return result;
    }
}

/////////
// DFS //
/////////

public class Solution {
    /* DFS */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res, 0);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res, int d){
        if(root == null){
            return;
        }
        /* Expand the list */
        if(d == res.size()){
            res.add(root.val);
        }
        else{
        /* Or Update the value */    
            res.set(d, Math.max(res.get(d), root.val));
        }
        helper(root.left, res, d + 1);
        helper(root.right, res, d + 1);
    }
}



