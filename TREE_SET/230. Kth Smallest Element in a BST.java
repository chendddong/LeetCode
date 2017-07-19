/**
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it.
 * -- LeetCode 230
 * -- TreeDemo 25.1
 */

/*
    Note: 
    You may assume k is always valid, which is between 1 and k.

    Follow up:
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */

///////////////////////
// Inorder traversal //
///////////////////////

public class Solution {
    /* Inorder traversal */    
    public int kthSmallest(TreeNode root, int k) {
        ArrayDeque<TreeNode> s = new ArrayDeque<>();
        TreeNode cur = root;

        /* Sequence of the number in BST */
        int count = 1;
        int value = 0;

        while (cur != null || !s.isEmpty()) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            cur = s.peek();
            s.pop();

            /* Solve problem */
            if (count == k) {
                value = cur.val;
            }
            count++;


            cur = cur.right;  
        }

        return value;
    }

}

////////////////////
// Carry a vector //
////////////////////

public class Solution {
    /* Got all the Inorder list and return the k - 1 the in the list */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        kthSmallestHelper(root, list);
        return list.get(k - 1);
    }
    private void kthSmallestHelper(TreeNode root,
                                   List<Integer> list) {
        if (root == null) {
            return;
        }
        kthSmallestHelper(root.left, list);
        list.add(root.val);
        kthSmallestHelper(root.right, list);
    }

}


///////////////////////
// Binary Search DFS //
///////////////////////

public class Solution {
    /*
        Tree 2: Binary Search Tree
                  100
                /    \
               40     180
             /  \     /
            30   60  110

         where r100 is the root
     */    
    /* Interesting solution, narrow down and k to get to the target point */
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k - 1 - count); /* 1 is counted as
            current node */
        }
        
        return root.val;
    }
    
    public int countNodes(TreeNode n) {
        if (n == null) return 0;
        
        return 1 + countNodes(n.left) + countNodes(n.right);
    }    
}
