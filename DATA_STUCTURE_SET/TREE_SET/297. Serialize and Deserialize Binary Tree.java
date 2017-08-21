/*
    Serialization is the process of converting a data structure or object into a
    sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 */

/*
    For example, you may serialize the following tree

        1
       / \
      2   3
         / \
        4   5
    as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
    Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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

////////////////////////////////
// Clean & Easy to understand //
////////////////////////////////

class Codec {
    /*
        The idea is simple: print the tree in pre-order traversal and use "X"
        to denote null node and split node with ",". We can use a StringBuilder
        for building the string on the fly. For deserializing, we use a Queue to store the pre-order traversal and since we have "X" as null node, we know exactly how to where to end building subtrees.
     */
    private static final String spliter = ",";
    private static final String Null = "#";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        /* Convert Array to List */
        nodes.addAll(Arrays.asList(data.split(spliter))); 
        return buildTree(nodes);   
    }    
    /* Preorderly traverse the tree and add nodes to sb */
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(Null).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left,  sb);
            buildString(node.right, sb);
        }
    }
    /* Build the node preorderly */
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove(); /* Remove from the front */

        if (val.equals(Null)) return null;
        
        TreeNode node = new TreeNode(Integer.valueOf(val)); /* Convert str */
        node.left  = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }

}

////////////////////////////////////////////////////////////////////////////   
/*
    Example:
    Tree 1: Basic Binary Tree
              1
            /   \
           2     3
         /  \     \
        4    5     6
      / \    /    / \
     9   10 11   7   8
    
    String after the serialization is:
    [1,2,4,9,#,#,10,#,#,5,11,#,#,#,3,#,6,7,#,#,8,#,#,]

    We can just use preorder traversal to restore the tree!
 */
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////
// BFS using a LinkedList as a Queue //
///////////////////////////////////////

class Codec {
    /* Need to go through the algorithm */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }

        /* 
            Use ArrayList as a queue instead of the deque since we are gonna
            manipulate the index.
        */
        ArrayList<TreeNode> queue = new ArrayList<>(); 
        queue.add(root);

        /* Add all the nodes to queue as well as nulls BFS */
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                continue;
            }

            queue.add(node.left);
            queue.add(node.right);
        }

        /* Remove all the last 'nulls' */
        while (queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }

        /* Serialization */
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        /* Offer root to the sb */
        sb.append(queue.get(0).val);

        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                /* Can append int directly */
                sb.append(queue.get(i).val);
            }
        }

        sb.append("}");
        return sb.toString();
    }
    
    public TreeNode deserialize(String data) {
        if (data.equals("{}"))
            return null;

        /* Split data, get the String array;Don't forget to cut the '{' '}' */
        String[] vals = data.substring(1, data.length() - 1).split(",");

        /* Creating queue and root node */
        ArrayList<TreeNode> queue = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));/* s to int */
        queue.add(root);

        int index = 0; /* For record the level */ 
        boolean isLeftChild = true;

        /* BFS process: Using index to access the parent one by one */
        for (int i = 1; i < vals.length; i++) {
            /* We got numbers */
            if (!vals[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                /* Connecting the tree */
                if (isLeftChild)
                    queue.get(index).left = node;
                else {
                    queue.get(index).right = node;
                }

                /* Offer the node to queue */
                queue.add(node);                
            }
            if (!isLeftChild) 
                index++;
            isLeftChild = !isLeftChild;

        }

        return root;
    }
}
