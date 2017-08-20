/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 */

/*
    For example:
    Given binary tree {1,#,2,3},
       1
        \
         2
        /
       3
    return [3,2,1].

    Note: Recursive solution is trivial, could you do it iteratively?
 */


//////////////////////
// Divide & Conquer //
//////////////////////

public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);

        results.addAll(left);
        results.addAll(right);
        results.add(root.val);

        return results;
    }
}

//////////////
// Traverse //
//////////////

public class Solution {
    /*
        Use str output, void method, null return;
        Left->Right->Root
    */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversalHelper(root, res);
        return res;
    }
    private void postorderTraversalHelper(TreeNode root,
                                          List<Integer> res) {
        if (root == null) return;
        postorderTraversalHelper(root.left, res);
        postorderTraversalHelper(root.right, res);
        res.add(root.val);
    }
}

/////////////////////////////
// Two Stack Non-Recursive //
/////////////////////////////

public class Solution {
    /*
        The postorder is the same as the inverse of the preorder from the
        right-hand side. So we use two stacks.
    */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayDeque<TreeNode> s = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> out = new ArrayDeque<TreeNode>();

        s.push(root);

        while(!s.isEmpty()) {
            TreeNode cur = s.pop();
            /* Solve later */
            out.push(cur);

            if (cur.left != null) {
                s.push(cur.left);
            }

            if (cur.right != null) {
                s.push(cur.right);
            }
        }

        while (!out.isEmpty()) {
            result.add(out.pop().val);
        }

        return result;
    }    
}

//////////////////////////
// Normal Non-Recursion //
//////////////////////////

public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<Integer>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode curr = root;
        TreeNode prev = null;
        if (root == null) {
            return postorder;
        }
        stack.push(root);
        
        while (!stack.isEmpty()) {
            curr = stack.peek();
            /* Traverse down the tree */
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                }else if (curr.right != null) {
                    stack.push(curr.right);
                }
            /* Traverse up the tree from the left */                
            } else if (curr.left == prev) { 
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else { /* Traverse up the tree from the right */
                postorder.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }

        return postorder;

    }
}

/*
    Thoughts:

    1. The difference between the traverse and DC method.
    
       ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
       The traverse method is like a knight doing all the stuff himself -- Ned
       Stark. He would go all the way to all the nodes carry the king's wish.

       There would always be a 'result' being carried around when the
       recursion happens.
       
       Whereas the DC method is like The King's small counsel each Lord would
       bring the message from his part and the king will process the message and
       merge the message with his own thoughts.

       Lord little finger and Lord Varys would do their own thing first and
       bring out the 'left' result and 'right' result and then solve the
       problem with the king together.
       ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */