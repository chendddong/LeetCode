/**
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path
 * between any two nodes in a tree. This path may or may not pass through the
 * root.
 *
 * -- TreeDeomo 12
 * -- LeetCode 543.
 */

/*
    Example:
    Given a binary tree 
              1
             / \
            2   3
           / \     
          4   5    
    Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

    Note: The length of path between two nodes is represented by the number of edges between them.
 */

/*
        Thoughts:

        What is the distance between two nodes: the edges between two nodes.
        Example:
                  1
                 / \
                2   3
               / \   \
              5  6    4
        So the distance between 2 and 4 is 3 and the max distance for the tree
        is the distance(the distance between far left node and far right node)
        between 5 and 4 which is 4.

        We will use ResultType to keep track of:
        1 -- the Depth of the node.
        2 -- the maxDistance of the current branch.

        Algorithm:
        1 -- calculate the Depth of left and right side, respectively;
             calculate the Distance of left and right side, respectively;
        2 -- Max distance is the max between the three:
            a. If it goes through the root, depth + 2
            b. Left side distance
            c. Right side distance
        3 -- Base case of the recursion:
            root == null, depth = -1, maxDistance = -1;
     */
public class Solution {
    private static class ResultType {
        int depth;
        int maxDistance;
        public ResultType(int depth, int maxDistance) {
            this.depth = depth;
            this.maxDistance = maxDistance;
        }
    }
    public static int diameterOfBinaryTree(TreeNode root) {
        return diameterOfBinaryTreeHelp(root).maxDistance;
    }
    private static ResultType diameterOfBinaryTreeHelp(TreeNode root) {
        /* Base (we can return -1 or 0 for maxDist based on our preference) */
        ResultType result = new ResultType(-1, 0);
        if (root == null) {
            return result;
        }

        /* Divide */
        ResultType left = diameterOfBinaryTreeHelp(root.left);
        ResultType right = diameterOfBinaryTreeHelp(root.right);


        /* The depth from the subtree to the root. */
        result.depth = Math.max(left.depth, right.depth) + 1;

        /* If the path go through the root, it should add 2 */
        int crossLen = left.depth + right.depth + 2;

        /* Calculate the largest between the 3 things */
        result.maxDistance = Math.max(left.maxDistance, right.maxDistance);
        result.maxDistance = Math.max(result.maxDistance, crossLen);

        return result;
    }    
}
    