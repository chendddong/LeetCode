/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * --LeetCode 116-117
 * --TreeDemo 4.5.1-4.5.4
 */

/*
    What if the given tree could be any binary tree? Would your previous solution still work?

    Note:

    You may only use constant extra space.
    For example,
    Given the following binary tree,
             1
           /  \
          2    3
         / \    \
        4   5    7
    After calling your function, the tree should look like:
             1 -> NULL
           /  \
          2 -> 3 -> NULL
         / \    \
        4-> 5 -> 7 -> NULL
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
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

////////////////////////////
// Level order traversal  //
////////////////////////////

public class Solution {
    /* 
        This algorithm will take O(n) time and O(1) space;
        Always, Always, Always try to use dummy node in LinkedList !!!
        Use a dummy node and a travel pointer
    */
    public void connect(TreeLinkNode root) {

        while (root != null) {
            /* For fresh and return */
            TreeLinkNode tempChild = new TreeLinkNode(0);
            /* For traversal -- pointer */
            TreeLinkNode currentChild = tempChild;
            //      1
            //    /  \
            //   2    3
            //  / \    \
            // 4   5    7
            while (root != null) {
                if (root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if (root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            /* Dummy Node */
            root = tempChild.next;
        }

    }
}
