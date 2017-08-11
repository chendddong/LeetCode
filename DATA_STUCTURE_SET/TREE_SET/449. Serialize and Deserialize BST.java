/*
    Serialization is the process of converting a data structure or object into a
    sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

    The encoded string should be as compact as possible.

    Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */


public class Codec {

    /* Very Important problem */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }

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

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("{}")) {
            return null;
        }              

        /* Split data and get the String array; Don't forget to cut the '{' */
        String[] vals = data.substring(1, data.length() - 1).split(",");

        /* Creating queue and root node */
        ArrayList<TreeNode> queue = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(root);

        int index = 0;
        boolean isLeftChild = true;

        /*
            Tree 2: Binary Search Tree
                      100
                    /    \
                   40     180
                 /  \     /
                30   60  110

             where r100 is the root
            String = "{100,40,180,30,60,110}"
         */ 

        /* BFS process; Using index to access the parent one by one */
        for (int i = 1; i < vals.length; i++) {
            /* We got numbers */
            if (!vals[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                /* Connecting the tree */
                if (isLeftChild) {
                    queue.get(index).left = node;
                } else {
                    queue.get(index).right = node;
                }

                /* Offer it to the queue as well */
                queue.add(node);
            }

            if (!isLeftChild) {
                index++;
            }
            isLeftChild = !isLeftChild;
        }

        return root;

    }

}

 