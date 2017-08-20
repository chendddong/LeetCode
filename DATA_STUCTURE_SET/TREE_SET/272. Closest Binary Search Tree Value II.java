/**
 * Given a non-empty binary search tree and a target value, find k values in 
 * the BST that are closest to the target.
 */

/*
    Note:
    Given target value is a floating point.
    You may assume k is always valid, that is: k ≤ total nodes.
    You are guaranteed to have only one unique set of k values in the BST that are closest to the target.


    Follow up:
    Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

    Example:

              100
            /    \
           40     180
         /  \     /
        30   60  110

    target is 80, k = 3, return 100, 60, 110
 */

////////////////
// Two stacks //
////////////////
    
class Solution {
    /*
        The idea is to compare the predecessors and successors of the closest node to the target, we can use two stacks to track the predecessors and successors, then like what we do in merge sort, we compare and pick the closest one to the target and put it to the result list.

        As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.

        We can use iterative inorder traversal rather than recursion, but to keep the code clean, here is the recursion version.    
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        ArrayDeque<Integer> pred = new ArrayDeque<>();
        ArrayDeque<Integer> succ = new ArrayDeque<>();

        inorder(root, target, false, pred); /* Predecessor */
        inorder(root, target, true,  succ); /* Successor */

        while (k-- > 0) {
            if (pred.isEmpty())
                res.add(succ.pop());
            else if (succ.isEmpty())
                res.add(pred.pop());
            else if (Math.abs(pred.peek() - target) < Math.abs(succ.peek() -
               target))
                res.add(pred.pop());
            else 
                res.add(succ.pop());
        }

        return res;
    }

    private void inorder(TreeNode root,
                         double target, 
                         boolean isSucc, 
                         ArrayDeque<Integer> stack) {
        if (root == null) return;

        /* Go deeper; Successor ? go right first : go left first */
        inorder(isSucc ? root.right : root.left, target, isSucc, stack);

        /* Early terminate. Note that we have to slit the < and > and = */
        if ((isSucc && root.val <= target) || (!isSucc && root.val > target))
            return;
        // if ((isSucc && root.val < target) || (!isSucc && root.val >= target))
        // return;        

        /* Inorder traversal: the root should always be in the middle */
        stack.push(root.val);

        /* Go deeper; Successor ? go left then : go right then */
        inorder(isSucc ? root.left : root.right, target, isSucc, stack);    

    }

}


///////////////////////////////////////////
// Two stacks with log(n) initialization //
///////////////////////////////////////////

public class Solution {
    /*
        I use a predecessor stack and successor stack. I do a log n traversal to initialize them until I reach the null node. Then I use the getPredecessor and getSuccessor method to pop k closest nodes and update the stacks.

        Time complexity is O(k log n), since k BST traversals are needed and each is bounded by O(log n) time. Note that it is not O(log n + k) which is the time complexity for k closest numbers in a linear array.

        Space complexity is O(k log n), since each traversal brings O(log n) new nodes to the stack.
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new LinkedList<Integer>();

        ArrayDeque<TreeNode> pred = new ArrayDeque<>();
        ArrayDeque<TreeNode> succ = new ArrayDeque<>();

        TreeNode curr = root;
        while (curr != null) { /* Takes log(n) */
            if (curr.val >= target) {
                succ.push(curr);
                curr = curr.left;
            } else {
                pred.push(curr);
                curr = curr.right;
            }
        }

        while (k-- > 0) {
            if (pred.isEmpty() && succ.isEmpty())
                break;
            else if (pred.isEmpty())
                result.add(getSuccessor(succ));
            else if (succ.isEmpty())
                result.add(getPredecessor(pred));
            else if (Math.abs(target - pred.peek().val) <
                     Math.abs(target - succ.peek().val))
                result.add(getPredecessor(pred));
            else
                result.add(getSuccessor(succ));
        }

        return result;
    }        

    private int getPredecessor(ArrayDeque<TreeNode> s) {
        TreeNode popped = s.pop();
        TreeNode p = popped.left; /* Pred would always be on the left */
        while (p != null) {
            s.push(p);
            p = p.right; /* But the nearest is always be its right */
        }
        return popped.val;
    }
    private int getSuccessor(ArrayDeque<TreeNode> s) {
        TreeNode popped = s.pop();
        TreeNode p = popped.right; /* Succ would always be on the right */

        while (p != null) {
            s.push(p);
            p = p.left; /* But the nearest is always be its left */
        }
        return popped.val;
    }    

}

////////////////////////////////
// Use LinkedList as a buffer //
////////////////////////////////

publ1ic class Solution {
    /*
        Use the LinkedList as:
            1. result
            2. a Heap!!!

        The structure is like the inorder traversal but we are dealing with the
        problem in the process.

        Actually this algorithm is pretty good and it costs 1 ms to run.
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        closestKValuesHelper(list, root, target, k);
        return list;
    }
    /* Return a boolean */
    private boolean closestKValuesHelper(LinkedList<Integer> list,
                                         TreeNode root,
                                         double target,
                                         int k) {
        if (root == null) return false; /* Means no nearest value */

        if (closestKValuesHelper(list, root.left, target, k)) {
            return true;
        }

        /* Update the list before adding the root.val */
        if (list.size() == k) {
            if (Math.abs(list.getFirst() - target) < 
                Math.abs(root.val - target))
                return true;
            else 
                list.removeFirst();
        }

        list.addLast(root.val);

        return closestKValuesHelper(list, root.right, target, k);
    }
}
