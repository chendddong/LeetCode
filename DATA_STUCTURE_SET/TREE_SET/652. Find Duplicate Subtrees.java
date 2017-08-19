/**
 * Given a binary tree, return all duplicate subtrees. For each kind of
 * duplicate subtrees, you only need to return the root node of any one of
 * them.
 */

/*
    Two trees are duplicate if they have the same structure with same node values.

    Example 1: 
            1
           / \
          2   3
         /   / \
        4   2   4
           /
          4
    The following are two duplicate subtrees:
          2
         /
        4
    and
        4
    Therefore, you need to return above trees' root in the form of a list.
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

//////////////////////////////////////////////////////
// PostOrder + getOrDefualt + String representation //      Quite brilliant
//////////////////////////////////////////////////////

class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        traverse(root, new HashMap<>(), res);
        return res;
    }

    public String traverse(TreeNode cur,
                            Map<String, Integer> map,
                            List<TreeNode> res) {
        if (cur == null) return "#";
        String serial = cur.val + "," + traverse(cur.left, map, res) + "," +
        traverse(cur.right, map, res); /* Preorder AC */
//         String serial = traverse(cur.left, map, res) + "," + traverse
// (cur.right,map, res) + cur.val;  /* Postorder AC */   
//        String serial = traverse(cur.left, map, res) + "," + cur.val + "," +
//        traverse(cur.right, map, res) ; /* Inorder No AC */              

        if (map.getOrDefault(serial, 0) == 1) res.add(cur);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
}


