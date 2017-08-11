*
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 */

/*
    Calling next() will return the next smallest number in the BST.

    Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 */

///////////////////
// Stack & Queue //
///////////////////

public class BSTIterator {
    ArrayDeque<TreeNode> s;
    ArrayDeque<TreeNode> q;
    public BSTIterator(TreeNode root) {
        /* Initialize the data structure */
        s = new ArrayDeque<>();
        q = new ArrayDeque<>();

        /* Inorder traversal */
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            cur = s.peek();
            s.pop();

            /* Add the smallest to the q */
            q.offer(cur);

            cur = cur.right;
        }

    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (!q.isEmpty()) {
            return true;
        }
        return false;    
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode nextNode = q.peek();
        q.poll();
        return nextNode.val;    
    }
}


////////////////////
// Just One Stack //
////////////////////

public class BSTIterator {
    private ArrayDeque<TreeNode> s = new ArrayDeque<TreeNode>();
    public BSTIterator(TreeNode root) {
        /* Left */
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        /* Root */
        TreeNode tmpNode = s.pop();
        /* Right */
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {
        while (node != null) {
            s.push(node);
            node = node.left;
        }
    }
}







