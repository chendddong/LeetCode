/*
    One way to serialize a binary tree is to use pre-order traversal. When we
    encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

         _9_
        /   \
       3     2
      / \   / \
     4   1  #  6
    / \ / \   / \
    # # # #   # #
    For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

    Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

    Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

    You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

    Example 1:
    "9,3,4,#,#,1,#,#,2,#,6,#,#"
    Return true

    Example 2:
    "1,#"
    Return false

    Example 3:
    "9,#,#,1"
    Return false
 */

/////////////////
// Degree Diff //
/////////////////

public class Solution {
    /*
        All non-null node provides 2 outdegree and 1 indegree (2 children and 1
        parent), except root; All null node provides 0 outdegree and 1 indegree (0 child and 1 parent).

        Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by 2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.    
     */
    
    /* Algorithm work through */
    //      _9_
    //     /   \
    //    3     2
    //   / \   / \
    //  4   1  #  6
    // / \ / \   / \
    // # # # #   # #  
    // 
    //  String    "9,3,4,#,#,1,#,#,2,#,6,#,#"  
    //  diff       2 3 4 3 2 3 2 1 2 1 2 1 0   
    //  return true
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node : nodes) {
            if (--diff < 0) {
                return false;
            }
            if (!node.equals("#")) {
                diff += 2;
            }
        }
        return diff == 0;
    }
}

///////////
// Stack //
///////////

public class Solution {
    /*
        Using a stack, scan the string left to right
        Case 1: we see a number, just push it to the stack
        Case 2: we see a #, check if the top of the stack is also #
            if so, pop() #, pop() the number in a while loop, until the top of
            the stack is not #
            if not, push it to stack
            at last, check if stack's size is 1 and stack top is #.

        It's not quite intuitive though.
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder == null) {
            return false;
        }


        /* Algorithm work through */
        //      _9_
        //     /   \
        //    3     2
        //   / \   / \
        //  4   1  #  6
        // / \ / \   / \
        // # # # #   # #  
        // 
        //  String    "9,3,4,#,#,1,#,#,2,#,6,#,#"  
        //                

        ArrayDeque<String> s = new ArrayDeque<>();
        String[] strs = preorder.split(",");
        for (int i = 0; i < strs.length; i++) {
            String curr = strs[i];
            while (curr.equals("#") && !s.isEmpty() && s.peek().equals(curr)) {
                s.pop();
                if (s.isEmpty()) {
                    return false;
                }
                s.pop();
            }
            s.push(curr);
        }

        return s.size() == 1 && s.peek().equals("#");
    }
}

//////////////////////////////
// With no extra Space cost //
//////////////////////////////

public class Solution {
    public boolean isValidSerialization(String preorder) {
        String s = preorder;
        boolean flag = true;

        /* Algorithm work through */
        //      _9_
        //     /   \
        //    3     2
        //   / \   / \
        //  4   1  #  6
        // / \ / \   / \
        // # # # #   # #          

        //  "9 , 3 , 4 , # , # , 1 , # , # , 2 , # , 6 , # , #"
        //             i
        //           s
        //  "9 , 3 , # , 1 , # , # , 2 , # , 6 , # , #"
        //                 i
        //               s
        //  "9 , 3 , # , # , 2 , # , 6 , # , #"
        //         i
        //       s   
        //  "9 , # , 2 , # , 6 , # , #"
        //                     i 
        //                   s
        //  "9 , # , 2 , # , #"
        //             i
        //           s
        //  "9 , # , #"
        //     i
        //   s
        //   "#"                           
        
        while (s.length() > 1) {
            int index = s.indexOf(",#,#");
            if (index < 0) {
                flag = false;
                break;
            }

            int start = index;
            while (start > 0 && s.charAt(start - 1) != ',') {
                start--;
            }

            if (s.charAt(start) == '#') {
                flag = false;
                break;
            }

            s = s.substring(0, start) + s.substring(index + 3);
        }

        return s.equals("#") && flag;  
    }
}