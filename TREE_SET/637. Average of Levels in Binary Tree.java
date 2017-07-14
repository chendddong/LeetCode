/**
 * Given a non-empty binary tree, return the average value of the nodes on
 * each level in the form of an array.
 */

/*
    Example 1:
    Input:
        3
       / \
      9  20
        /  \
       15   7
    Output: [3, 14.5, 11]
    Explanation:
    The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
    Note:
    The range of node's value is in the range of 32-bit signed integer.
 */

public class Solution {
    /* We can use DFS to traverse each level and use sum and size to get the avg.*/
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            long sum = 0;
            /* For a certain level */
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            
            /* Deal with the edge cases */
            double avg = (double) sum / size;
            if (avg > Integer.MAX_VALUE) {
                result.add((double) Integer.MAX_VALUE);
            } else {
                result.add(avg);   
            }
        }
        return result;
    }
}





    

