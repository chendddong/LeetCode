/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the
 * most frequently occurred element) in the given BST.
 */

/*
    Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than or equal to the node's key.
    The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
    Both the left and right subtrees must also be binary search trees.
    For example:
    Given BST [1,null,2,2],
       1
        \
         2
        /
       2
    return [2].

    Note: If a tree has more than one mode, you can return them in any order.

    Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

 */

//////////////////////////////////
// Hash Map Use Map's Function  //
//////////////////////////////////

public class Solution {
    /* Inorder traversal */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        /* Use a hashMap -- Extra space */
        HashMap<Integer, Integer> map = new HashMap<>();

        /* Preorder traversal */
        ArrayDeque<TreeNode> s = new ArrayDeque<>();
        TreeNode cur = root;
        while (!s.isEmpty() || cur != null) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            cur = s.peek();
            s.pop();

            /* Solve problem */
            map.merge(cur.val, 1, Integer::sum);

            cur = cur.right;
        }

        ArrayList<Integer> temp = new ArrayList<>();


        /* Get the max value of the key */
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
            maxEntry = entry;
          }
        }
        Integer maxValue = maxEntry.getValue(); 

        /* Get all the keys that has the same maxValue */
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          if (entry.getValue() == maxValue) {
            temp.add(entry.getKey());
          }
        } 

        /* Get the answer */
        int[] result = new int[temp.size()];
        int index = 0;
        for (Integer key : temp) {
            result[index++] = key;
        }
        return result;
    }
}

/////////////////////
// Traversal Twice //
/////////////////////


public class Solution {
    /*
        I think the way to do it properly is to do two passes. One to find the
        highest number of occurrences of any value, and then a second pass to collect all values occurring that often.
     */   
    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    
    private int[] modes;

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }
}





