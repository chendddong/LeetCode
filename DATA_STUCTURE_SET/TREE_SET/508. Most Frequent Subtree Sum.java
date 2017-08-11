/*
    Given the root of a tree, you are asked to find the most frequent subtree sum.

    The subtree sum of a node is defined as the sum of all the node values
    formed by the subtree rooted at that node (including the node itself).
    So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 */

/*
    Examples 1
    Input:

      5
     /  \
    2   -3
    return [2, -3, 4], since all the values happen only once, return all of them in any order.
    Examples 2
    Input:

      5
     /  \
    2   -5
    return [2], since 2 happens twice, however -5 only occur once.
    Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

 */

public class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ArrayList<Integer> tmpResult = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSumHelper(root, map);

        /* Get those things from map */
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        Integer maxValue = maxEntry.getValue();
        /* Get all the keys that has the maxValue */
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValue) {
                tmpResult.add(entry.getKey());
            }
        }

        /* For the answer */
        int[] result = new int[tmpResult.size()];
        int index = 0;
        for (Integer key : tmpResult) {
            result[index++] = key;
        }
        return result;      
    }
    /* DFS to access the nodes */
    private void findFrequentTreeSumHelper(TreeNode root,
                                           HashMap<Integer, Integer> map) {
        if (root == null) {
            return;
        }

        findFrequentTreeSumHelper(root.left, map);
        findFrequentTreeSumHelper(root.right, map);
        
        /* Add sum to the map */
        map.merge(findNodesSum(root), 1, Integer::sum);
    }
    /* BFS find sums */
    private int findNodesSum(TreeNode root) {
        int sum = 0;
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            sum += node.val;
            if (node.right != null) {
                q.offer(node.right);
            }
            if (node.left != null) {
                q.offer(node.left);
            }
        }
        return sum;
    }
}

public class Solution {

    Map<Integer, Integer> sumToCount;
    int maxCount;

    public int[] findFrequentTreeSum(TreeNode root) {
        maxCount = 0;
        sumToCount = new HashMap<Integer, Integer>();
        
        postOrder(root);
        
        /* Find the key of the keyValue that is the maxCount */
        List<Integer> res = new ArrayList<>();
        for (int key : sumToCount.keySet()) {
            if (sumToCount.get(key) == maxCount) {
                res.add(key);
            }
        }
        
        /* Convert vector to array */
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
    /* Access all the nodes by postOrder */
    private int postOrder(TreeNode root) {
        if (root == null) return 0;
        
        int left = postOrder(root.left);
        int right = postOrder(root.right);
        int sum = left + right + root.val;

        /*  
            Solve the problem;
            This is new: getOrDefault. 
         */
        int count = sumToCount.getOrDefault(sum, 0) + 1;
        sumToCount.put(sum, count);

        /* For the convenience of using the map later */
        maxCount = Math.max(maxCount, count);
        
        return sum;
    }
}