/**
 * Given a binary tree, return all root-to-leaf paths.
 * -- LeetCode 257
 */

/*
    For example, given the following binary tree:

       1
     /   \
    2     3
     \
      5
    All root-to-leaf paths are:

    ["1->2->5", "1->3"]
 */

public class Solution {
    /* DFS Recursive by using a helper */
    public List<String> binaryTreePaths(TreeNode root) {
        /* Edge */
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        /* Pass those things we need */
        String branch = "";
        binaryTreePathsHelper(root, result, branch); 
        return result;   
    }
    private void binaryTreePathsHelper(TreeNode root, List<String> result,
        String branch) {
        if (root == null) {
            return;
        }
        /* Leaf */
        if (root.left == null && root.right == null) {
            branch = branch + root.val; 
            result.add(branch);
            /* backtracking */
            branch = "";   
        } else {
            branch = branch + root.val + "->";
        }

        /* Go deeper */
        binaryTreePathsHelper(root.left, result, branch);
        binaryTreePathsHelper(root.right, result, branch);           
    }
}

public class Solution {
    /* Use for loop to get all the paths from left and right side; No helper */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();

        if(root == null) {
            return paths;
        }

        /* Leaf */
        if(root.left == null && root.right == null){
            paths.add(root.val + "");
            return paths;
        }

         for (String path : binaryTreePaths(root.left)) {
             paths.add(root.val + "->" + path);
         }

         for (String path : binaryTreePaths(root.right)) {
             paths.add(root.val + "->" + path);
         }

         return paths;
        
    }
}


 