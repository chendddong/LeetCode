/**
 * You need to construct a string consists of parenthesis and integers from a 
 * binary tree with the preorder traversing way.
 */

/*
  The null node needs to be represented by empty parenthesis pair "()". And you 
  need to omit all the empty parenthesis pairs that don't affect the one-to-one 
  mapping relationship between the string and the original binary tree.

  Example 1:
  Input: Binary tree: [1,2,3,4]
         1
       /   \
      2     3
     /    
    4     

  Output: "1(2(4))(3)"

  Explanation: Originallay it needs to be "1(2(4)())(3()())", 
  but you need to omit all the unnecessary empty parenthesis pairs. 
  And it will be "1(2(4))(3)".
  Example 2:
  Input: Binary tree: [1,2,3,null,4]
         1
       /   \
      2     3
       \  
        4 

  Output: "1(2()(4))(3)"

  Explanation: Almost the same as the first example, 
  except we can't omit the first parenthesis pair to break the one-to-one 
  mapping relationship between the input and the output.

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

////////////////////////////
// Solution #0 Recursion  //
////////////////////////////


public class Solution {
    /* Just add parenthesis to the right place */
    public String tree2str(TreeNode t) {
        if(t == null)
            return "";
        // 1
        if(t.left == null && t.right == null)
            return t.val + "";
        //    1
        //   /
        //  2 
        if(t.right == null)
            return t.val + "(" + tree2str(t.left) + ")";
        //  1          1
        //   \   or   / \
        //    2      2   3
        return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";   
    }
}

public class Solution {

    public String tree2str(TreeNode t) {
        if (t == null)
            return "";

        /* For traversal */
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(t);
        /* Mark the visited node */
        Set<TreeNode> visited = new HashSet<>();
        String s = "";
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s += ")";
            } else {
                /* Solve the problem */
                visited.add(t);
                s += "(" + t.val;
                if (t.left == null && t.right != null)
                    s += "()";

                /* Preorder traversal part */
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        /* Cut the unnecessary part */
        return s.substring(1, s.length() - 1);
    }
}
