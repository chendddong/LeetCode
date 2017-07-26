/**
 * Given a binary tree, return the vertical order traversal of its nodes'
 * values.
 * (ie, from top to bottom, column by column).
 */

/*
    If two nodes are in the same row and column, the order should be from left to right.

    Examples:

    Given binary tree [3,9,20,null,null,15,7],
       3
      /\
     /  \
     9  20
        /\
       /  \
      15   7
    return its vertical order traversal as:
    [
      [9],
      [3,15],
      [20],
      [7]
    ]
    Given binary tree [3,9,8,4,0,1,7],
         3
        /\
       /  \
       9   8
      /\  /\
     /  \/  \
     4  01   7
    return its vertical order traversal as:
    [
      [4],
      [9],
      [3,0,1],
      [8],
      [7]
    ]
    Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
         3
        /\
       /  \
       9   8
      /\  /\
     /  \/  \
     4  01   7
        /\
       /  \
       5   2
    return its vertical order traversal as:
    [
      [4],
      [9,5],
      [3,0,1],
      [8,2],
      [7]
    ]
 */

////////////////////////////
// TreeMap BFS Two Queues //
////////////////////////////

public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }    

        Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        ArrayDeque<Integer> qCol = new ArrayDeque<>();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        qCol.offer(0);

        /* Go through */
        // Given binary tree [3,9,20,null,null,15,7],
        //    3
        //   /\
        //  /  \
        //  9  20
        //     /\
        //    /  \
        //   15   7
        // return its vertical order traversal as:
        // [
        //   [9],
        //   [3,15],
        //   [20],
        //   [7]
        // ]

        /* BFS ! And we can't use DFS since it's required to top to bottom */
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int col = qCol.poll();

            /* Judge if there are any keys before adding the keys */
            if (!map.containsKey(col)) {
                /* Bit tricky tho for the treeMap */
                map.put(col, new ArrayList<Integer>(Arrays.asList(curr.val)));
            } else {
                map.get(col).add(curr.val);
            }

            if (curr.left != null) {
                queue.offer(curr.left);
                qCol.offer(col - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                qCol.offer(col + 1);
            }
        }

        for (int n : map.keySet()) {
            result.add(map.get(n));
        }

        return result;
    }
}


///////////////////////////////////////////
// Stretch the index and Using a HashMap //
///////////////////////////////////////////

public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        int colMin = 0;
        int colMax = 0;
        Map<Integer, List<Integer>> col = new HashMap<>();

        ArrayDeque<Integer> q1 = new ArrayDeque<>(); /* For col */
        ArrayDeque<TreeNode> q2 = new ArrayDeque<>(); /* For node */

        q1.add(0);
        q2.add(root);

        while (!q1.isEmpty()) {
            int c = q1.poll();
            TreeNode node = q2.poll();

            /* Map's empty key */
            if (!col.containsKey(c)) {
                col.put(c, new ArrayList<Integer>());
            } 
            col.get(c).add(node.val);

            /* The essence: stretch the index and iterate from the smallest */
            colMin = Math.min(colMin, c);
            colMax = Math.max(colMax, c);

            if (node.left != null) {
                q1.add(c - 1);
                q2.add(node.left);
            }
            if (node.right != null) {
                q1.add(c + 1);
                q2.add(node.right);
            }
        }

        /* Iterate the map key and add its values */
        for (int i = colMin; i <= colMax; i++) {
            ans.add(col.get(i));
        }

        return ans;


    }
}


