/**
 * Invert a binary tree. -- LeetCode 226
 * Invert a binary tree. -- LintCode 175
 * Mirror a binary tree. -- TreeDemo 10
 * 
 */

/*
         4
       /   \
      2     7
     / \   / \
    1   3 6   9

    to
         4
       /   \
      7     2
     / \   / \
    9   6 3   1

    Trivia:
    This problem was inspired by this original tweet by Max Howell:
    Google: 90% of our engineers use the software you wrote (Homebrew), but you 
    can’t invert a binary tree on a white board so fuck off.
 */

///////////////////////////////
// Solution #0 recursive DFS //
///////////////////////////////

public class Solution {
    /* it's easy to write and pretty much concise. This won't change the tree */
    public TreeNode invertTree(TreeNode root) {
        
        if (root == null) {
            return null;
        }

        /* Deep copying */
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = invertTree(right);
        root.right = invertTree(left);

        return root;
    }
}

//////////////////////////////////////////////
// Solution #1 recursive DFS Break the tree //
//////////////////////////////////////////////

public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left  = invertTree(root.left);
        
        root.right = left;
        root.left = right;
        
        return root;
    }
}

///////////////////////////
// Solution #2 DFS Stack //
///////////////////////////

/*
    The solution #0 is correct, but it is also bound to the application 
    stack, which means that it's no so much scalable - (you can find the problem
    size that will overflow the stack and crash your application), so more 
    robust solution would be to use stack data structure.
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        
        if (root == null) {
            return null;
        }

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        while(!stack.isEmpty()) {

            /* Solving the problem*/
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }
}

///////////////////////////
// Solution #3 DFS Queue //
///////////////////////////

/*
    Finally we can easily convert the above solution to BFS - or so called level 
    order traversal.
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        
        if (root == null) {
            return null;
        }

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            /* Solving the problem*/            
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}