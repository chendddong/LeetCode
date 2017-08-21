/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 */

/*
    Recover the tree without changing its structure.

    Note:
    A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */

/////////////////////////////////
// Inorder Traversal Recursive //
/////////////////////////////////

class Solution {
    /* 
        Draw the tree and randomly swap two of them we then can see where the
        first and second are 
     */
    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        inorder99(root);
        recoverSwap();
    }
    private void inorder99(TreeNode root) {
        if (root == null) return;
        /* Go left */
        inorder99(root.left);
        /* Solve problem in between */
        if (prev != null && prev.val >= root.val) {
            if (first == null)
                first = prev; /* Smaller incorrect one found as prev */
            second = root; /* Bigger incorrect one found as root */
        }
        /* Update prev */
        prev = root; 
        /* Go right */
        inorder99(root.right);
    }
    private void recoverSwap() {
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}

/////////////////////////////////
// Inorder Traversal Iterative //
/////////////////////////////////

class Solution {
    /*
        Same idea.
        Must know inorder traversal from the heart.
     */
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        TreeNode cur = root;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            stack.pop();

            if (prev != null && cur.val <= prev.val) { /* <= */
                if (first == null)
                    first = prev;
                second = cur;
            }

            prev = cur;
            cur = cur.right;
        }

        /* Swap value */
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}

//////////////////////
// Morris-traversal //  Follow up
//////////////////////

class Solution {
/*
    To reduce the space to constant space, we have to use Morris-traversal.

    Morris-traversal is similar to recursive/iterative traversal, but we need to modify the tree structure during the
    traversal. before we visiting the left tree of a root, we will build a back-edge between rightmost node in left tree and the root. So we can go back to the root node after we are done with the left tree. Then we locate the rightmost node in left subtree again, cut the back-edge, recover the tree structure and start visit right subtree. The detection of two incorrect TreeNodes is similar to iterative/recursive in-order traversal.
    We don't use extra data structure here, so the space complexity is reduced
    to O(1) and the time complexity will be O(n).
 */    
    public void recoverTree(TreeNode root) {    
        TreeNode first = null;
        TreeNode second = null;
        
        TreeNode pred = null; /* Rightmost node in left tree */
        TreeNode prev = null; 
        
        TreeNode cur = root;
        
        while(cur != null){
            /* we compare it with prev node as we did in in-order-traversal */
            if(prev != null && cur.val <= prev.val){
                if(first == null) first = prev;
                second = cur;
            }
            
            if(cur.left != null){
                /* Go left tree, locate its rightmost node in left tree */
                pred = cur.left;
                /* Connect the rightmost node with cur node (root node) */
                while (pred.right != null && pred.right != cur){
                    pred = pred.right;
                }
                
                if(pred.right == cur){
                    /* 
                        If this left tree has been visited before, then we are
                        done with it cut the connection with currNode and start
                        visit cur's right tree
                     */
                    pred.right = null;
                    prev = cur;
                    cur = cur.right;
                }else{
                    /*
                        If this left tree has not been visited before, then we
                        create a back edge from rightmost node to cur node, so we can return to the start point after done the left tree
                     */
                    pred.right = cur;
                    cur = cur.left;
                }
                
            }else{
                /* No left tree, then just visit its right tree */
                prev = cur;
                cur = cur.right;
            }
        }
        
        /*Swap*/
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}



