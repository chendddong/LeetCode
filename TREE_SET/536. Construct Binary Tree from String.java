/**
 * You need to construct a binary tree from a string consisting of parenthesis
 * and integers.
 */

/*
    The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

    You always start to construct the left child node of the parent first if it exists.

    Example:
    Input: "4(2(3)(1))(6(5))"
    Output: return the tree root node representing the following tree:

           4
         /   \
        2     6
       / \   / 
      3   1 5   
    Note:
    There will only be '(', ')', '-' and '0' ~ '9' in the input string.
    An empty tree is represented by "" instead of "()".
 */

////////////////////////
// Recursive For loop //
////////////////////////

public class Solution {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int firstParent = s.indexOf( "(" );

        int val = 0;
        /* No "(" means there is only root */
        if  (firstParent == - 1) {
            val = Integer.parseInt(s);
        /* Got the cur root's val */
        } else {
            val = Integer.parseInt(s.substring(0, firstParent));
        }

        TreeNode cur = new TreeNode(val);

        if (firstParent == -1) {
            return cur;
        }

        int start = firstParent;
        int leftParentCount = 0;

        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftParentCount++;
            } else if (s.charAt(i) == ')') {
                leftParentCount--;
            }

            if (leftParentCount == 0 && start == firstParent) {
                cur.left = str2tree(s.substring(start + 1, i));
                start = i + 1;
            } else if (leftParentCount == 0) {
                cur.right = str2tree(s.substring(start + 1, i));
            }

        }
        return cur;
    }

}


///////////////////////
// Another Recursive //
///////////////////////

public class Solution {
    /* Important one */
    public TreeNode str2tree(String s) {
        /* Base */
        if (s.length() == 0) return null;
        
        /* Create root */
        int i = 0, j = 0;

        /* j is not out of bond; char at j is a digit or '-' */        
        while (j < s.length() && (Character.isDigit(s.charAt(j)) || s.charAt(j) == '-')) j++;
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(i, j)));
        
        // "4(2(3)(1))(6(5))"

        /* Left Child */
        if (j < s.length()) {
            i = j;
            int count = 1;
            while (j + 1 < s.length() && count != 0) {
                j++;
                if (s.charAt(j) == ')') count--;
                if (s.charAt(j) == '(') count++;
            }
            root.left = str2tree(s.substring(i + 1, j));
        }
        
        j++;
        /* Right child */ 
        if (j < s.length()) {
            root.right = str2tree(s.substring(j + 1, s.length() - 1));
        }
        
        return root;
    }
}




