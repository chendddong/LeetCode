/**
 * Given a binary search tree with non-negative values, find the minimum 
 * absolute difference between values of any two nodes.
 */

/*
    Example:

    Input:

       1
        \
         3
        /
       2

    Output:
    1

    Explanation:
    The minimum absolute difference is 1, which is the difference between 2 and
    1 (or between 2 and 3).
    Note: There are at least two nodes in this BST.
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

////////////////////////////////////
// Solution #0 In-Order Traversal //
////////////////////////////////////

/*
    The most common idea is to first inOrder traverse the tree and compare the 
    delta between each of the adjacent values. It's guaranteed to have the 
    correct answer because it is a BST thus inOrder traversal values are sorted.
 */


/* In-Order traverse, time O(N), space complexity O(1). */
public class Solution {
    int min = Integer.MAX_VALUE;
    Integer prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        /* Base */
        if (root == null) {
            return min;
        }
        
        /* Go far left */
        getMinimumDifference(root.left);
        
        /* Not leaf */
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        /* Record the lastNode*/
        prev = root.val;
        
        /* After dealing with left, go right */
        getMinimumDifference(root.right);
        
        return min;
    }
    
}

//////////////////////////////////////
// Solution #1 General BT ArrayList //
//////////////////////////////////////

public class Solution {
    /*
        1. Get the list of the nodes value and sort it.
        2. Get the abs by pairs and update the min.
        3. Run time is n • log(n) since we used a sorting method.
     */
    public int getMinimumDifference(TreeNode root) {        
        ArrayList<Integer> nodeList = getTheList(root);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nodeList.size(); i++) {
            int temp = Math.abs(nodeList.get(i - 1) - nodeList.get(i));
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }
    /* There arr at least two nodes so skip the checking */
    private ArrayList<Integer> getTheList(TreeNode root) {
        ArrayList<Integer> nodeList = new ArrayList<>();
        /* Preorder traversal */
        ArrayDeque<TreeNode> s = new ArrayDeque<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            nodeList.add(node.val);
            if (node.right != null) {
                s.push(node.right);
            }
            if (node.left != null) {
                s.push(node.left);
            }
        }
        Collections.sort(nodeList);
        return nodeList;
    }
}

/////////////////////////////////
// Solution #2 Using a TreeSet //
/////////////////////////////////

/*
    The idea is to put values in a TreeSet and then every time we can use O(lgN)
    time to lookup for the nearest values.
 */

/* Preorder traverse, time O(NlgN), space O(N).*/

public class Solution {
    TreeSet<Integer> set = new TreeSet<>();
    int min = Integer.MAX_VALUE;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }
        /*
            The floor(E e) method is used to return the greatest element in this
            set less than or equal to the given e, or null if there is no such 
            element.
         */
        
        /*
            The ceiling(E e) method is used to return the least element in this 
            set greater than or equal to the given element, or null if there is 
            no such element.
         */
        if (!set.isEmpty()) {
            /* The value is the closest among all the number that is <= root */
            if (set.floor(root.val) != null) {
                min = Math.min(min, root.val - set.floor(root.val));
            }
            /* The value is the closest among all the number that is >= root */ 
            if (set.ceiling(root.val) != null) {
                min = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }
        
        set.add(root.val);
        
        getMinimumDifference(root.left);
        getMinimumDifference(root.right);
        
        return min;
    }
}
