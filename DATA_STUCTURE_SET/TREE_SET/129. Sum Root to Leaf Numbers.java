/*
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 */

/*
    An example is the root-to-leaf path 1->2->3 which represents the number 123.

    Find the total sum of all root-to-leaf numbers.

    For example,

        1
       / \
      2   3
    The root-to-leaf path 1->2 represents the number 12.
    The root-to-leaf path 1->3 represents the number 13.

    Return the sum = 12 + 13 = 25.
 */

///////////////
// Naive DFS //
///////////////

public class Solution {
    /* 
       Naive method: 
       1. Get the string list of paths.
       2. Get the sum
     */
    public int sumNumbers(TreeNode root) {
        List<String> allPaths = getPaths(root);
        return getPathsSum(allPaths);
    }
    private int getPathsSum(List<String> result) {
        int sum = 0;
        for (String s : result) {
            sum += Integer.parseInt(s);
        }
        return sum;
    }
    private List<String> getPaths(TreeNode root) {
        List<String> allPaths = new ArrayList<>();
        if (root == null) {
            return allPaths;
        }
        String onePath = "";
        getPathsHelper(root, allPaths, onePath);
        return allPaths;
    }
    private void getPathsHelper(TreeNode root,
                                List<String> allPaths, 
                                String onePath) {
        if (root == null) {
            return;
        }

        onePath += root.val;

        /* Leaf */
        if (root.left == null && root.right == null) {
            allPaths.add(onePath);
            /* Backtracking */
            onePath = "";
        }

        /* Go deeper */
        getPathsHelper(root.left, allPaths, onePath);
        getPathsHelper(root.right, allPaths, onePath);

    }
}

//////////////////////////
// DFS carry the weight //
//////////////////////////

public class Solution {
    /* Get the sum along the way; Top down carry the sum with the level */
    /* Just brilliant */
    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, 0);       
    }

    private int sumNumbersHelper(TreeNode root, int d) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            return d * 10 + root.val;
        }

        return sumNumbersHelper(root.left, d * 10 + root.val) +
               sumNumbersHelper(root.right, d * 10 + root.val);
    }

}

 * 15.4 Sum Root to Leaf Numbers
 *     1) sumNumbersNaive
 *     2) sumNumbersCarry