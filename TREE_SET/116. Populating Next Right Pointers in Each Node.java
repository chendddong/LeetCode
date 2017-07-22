/*
    Given a binary tree

        struct TreeLinkNode {
          TreeLinkNode *left;
          TreeLinkNode *right;
          TreeLinkNode *next;
        }

    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

    Initially, all next pointers are set to NULL.

    Note:

    You may only use constant extra space.
    You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
    For example,
    Given the following perfect binary tree,
             1
           /  \
          2    3
         / \  / \
        4  5  6  7
    After calling your function, the tree should look like:
             1 -> NULL
           /  \
          2 -> 3 -> NULL
         / \  / \
        4->5->6->7 -> NULL
 */

/////////
// BFS //
/////////

public class Solution {
    /* BFS will do the work; It is too slow though */
    public void connect(TreeLinkNode root) {
        connectBFS(root);
    }
    private void connectBFS(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        /* BFS */
        ArrayDeque<TreeLinkNode> q = new ArrayDeque<>();
        /* Root */
        q.offer(root);
        root.next = null;
        TreeLinkNode leftLast = null;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = q.poll();

                /* Solve problem by connecting */
                if (i == 0) {
                    leftLast = node;
                } else if (i == size - 1) {
                    leftLast.next = node;
                    node.next = null;
                } else {
                    leftLast.next = node;
                    leftLast = node;                    
                }

                /* Add children */
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }  
    }
}

/////////
// DFS //
/////////

public class Solution {

    /* This solution is so brilliant since it's not using extra space !! */
    public void connect(TreeLinkNode root) {
        if(root == null) {
            return;
        }
            
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }
        
        connect(root.left);
        connect(root.right);
    }
}

