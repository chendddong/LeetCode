/**
 * Given the root of a binary tree, then value v and depth d, you need to add a
 * row of nodes with value v at the given depth d. The root node is at depth 1.
 */

/*
    The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.

    Example 1:
    Input: 
    A binary tree as following:
           4
         /   \
        2     6
       / \   / 
      3   1 5   

    v = 1

    d = 2

    Output: 
           4
          / \
         1   1
        /     \
       2       6
      / \     / 
     3   1   5   

    Example 2:
    Input: 
    A binary tree as following:
          4
         /   
        2    
       / \   
      3   1    

    v = 1

    d = 3

    Output: 
          4
         /   
        2
       / \    
      1   1
     /     \  
    3       1
    Note:
    The given d is in range [1, maximum depth of the given tree + 1].
    The given binary tree has at least one tree node.
 */


////////////////////
// Recursion(DFS) //
////////////////////

public class Solution {
    /*
        Complexity Analysis

        Time complexity : O(n). A total of n nodes of the given tree will be considered.

        Space complexity : O(n). The depth of the recursion tree can go up
        to n in the worst case(skewed tree).
     */

    public TreeNode addOneRow(TreeNode root, int v, int d) {

        /* What's-the-pointedly weird; Edge case */
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }   
        addOneRowInsert(d,v,root,1); 
        return root;
    }
    private void addOneRowInsert(int maxD, 
                                     int insertVal,
                                     TreeNode root,
                                     int curD) {
        /* Base case */
        if (root == null) {
            return;
        }

        /* Solve problem: Store the original to temp and reconnect them */
        if (curD == maxD - 1) {
            TreeNode temp = root.left;
            root.left = new TreeNode(insertVal);
            root.left.left = temp;
            temp = root.right;
            root.right = new TreeNode(insertVal);
            root.right.right = temp;
        } else {
            addOneRowInsert(maxD, insertVal, root.left, curD + 1);
            addOneRowInsert(maxD, insertVal, root.right, curD + 1);
        }

    }
} 

//////////////////////
// Using queue(BFS) //
//////////////////////


public class Solution {
    /*
        Complexity Analysis

        Time complexity : O(n)O. A total of n nodes of the given tree will be considered in the worst case.

        Space complexity : O(x). The size of the queue or temp queue can grow up to x only. Here, x refers to the number of maximum number of nodes at any level in the given tree.
     */    
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 0;

        while (!q.isEmpty()) {
            depth++;
            /* Get size beforehand */
            int size = q.size();

            /* We found the right level */
            if (depth == d - 1) {
                /* Reconnect nodes for every node on d - 1 level */
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    
                    TreeNode temp = node.left;
                    node.left = new TreeNode(v);
                    node.left.left = temp;
                    temp = node.right;
                    node.right = new TreeNode(v);
                    node.right.right = temp;                     
                }
            } else { /* Normal DFS go deeper */
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (node.left != null) {
                        q.offer(node.left);
                    }
                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }                
            }
        }
        return root;
    }
}
