/**
 * Given n processes, each process has a unique PID (process id) and its PPID
 * (parent process id).
 */

/*
    Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

    We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

    Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

    Example 1:
    Input: 
    pid =  [1, 3, 10, 5]
    ppid = [3, 0, 5, 3]
    kill = 5
    Output: [5,10]
    Explanation: 
               3
             /   \
            1     5
                 /
                10
    Kill 5 will also kill 10.
    Note:
    The given kill id is guaranteed to be one of the given PIDs.
    n >= 1.    
 */

//////////////////////////////////////////////
// Depth First Search [Time Limit Exceeded] //
//////////////////////////////////////////////



public class Solution {
    /*
        Since killing a process leads to killing all its children processes, the
        simplest solution is to traverse over the ppid array and find out all
        the children of the process to be killed. Further, for every child
        chosen to be killed we recursively make call to the killProcess function now treating this child as the new parent to be killed. In every such call, we again traverse over the ppid array now considering the id of the child process, and continue in the same fashion. Further, at every step, for every process chosen to be killed, it is added to the list result that needs to be returned at the end.

        Complexity Analysis:

            Time complexity : O(n^n) function calls will be made in the worst
            case.

            Space complexity : O(n). The depth of the recursion tree can go up to n.
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> resultList = new ArrayList<>();
        List<Integer> killList = new ArrayList<>();
        killList.add(kill);
        helper(killList, pid, ppid, resultList);
        return resultList;
    }

    private void helper(List<Integer> killList,
                        List<Integer> pid,
                        List<Integer> ppid,
                        List<Integer> result) {
        if (killList.size() == 0) {
            return;
        }

        result.addAll(killList);
        ArrayList<Integer> killTemp = new ArrayList<>();

        int ppidSize = ppid.size();
        for (int kill : killList) {
            for (int j = 0; j < ppidSize; j++) {
                if (kill == ppid.get(j)) {
                    killTemp.add(pid.get(j));
                }
            }
        }

        helper(killTemp, pid, ppid, result);

    }
}


/////////////////////
// Tree Simulation //
/////////////////////


public class Solution {

    /* 
        Simulate the tree by creating the B-tree like tree with multiChildren.

        Time complexity : O(n). We need to traverse over the ppid and pid
        array of size n once. The getAllChildren function also takes n time, since no node can be a child of two nodes.

        Space complexity : O(n)O(n). map of size n is used.       
     */
    class Node {
        int val;
        List<Node> children = new ArrayList<>();
    }
    public List<Integer> killProcess(List<Integer> pid,
                                     List<Integer> ppid,
                                     int kill) {
        /* id <-> TreeNode */
        HashMap<Integer, Node> map = new HashMap<>();
        for (int id : pid) {
            Node node = new Node();
            node.val = id;
            map.put(id, node);
        }

        /* Traverse the parent id and add pid to its children list */
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                Node par = map.get(ppid.get(i));
                par.children.add(map.get(pid.get(i)));
            }
        }

        /* Recursively get all the children and add them to the result */
        List<Integer> result = new ArrayList<>();
        result.add(kill);
        getAllChildren(map.get(kill), result);
        return result;
    }
    private void getAllChildren(Node node, List<Integer> result) {
        for (Node n : node.children) {
            result.add(n.val);
            getAllChildren(n, result);
        }
    }
}


//////////////////////////////////
// HashMap + Depth First Search //
//////////////////////////////////

public class Solution {

    /* 
        Use hashMap and id <-> list of id's children data structure.

        Time complexity : O(n). We need to traverse over the ppid array of size n once. The getAllChildren function also takes n time, since no node can be a child of two nodes.

        Space complexity : O(n). map of size n is used.
     */
    public List<Integer> killProcess(List<Integer> pid,
                                     List<Integer> ppid,
                                     int kill) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                /* Get the children of a parent; if null create a new list.

                   Translate: if ppid.get(i) is null, set ppid(i) with a new
                   list.
                 */
                List<Integer> l = map.getOrDefault(ppid.get(i), new 
                ArrayList<Integer>());
                l.add(pid.get(i));
                map.put(ppid.get(i), l);
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(kill);
        getAllChildren(map, kill, result);
        return result;
    }

    private void getAllChildren(HashMap<Integer, List<Integer>> map,
                                int kill,
                                List<Integer> result) {
        if (map.containsKey(kill)) {
            for (int id : map.get(kill)) {
                result.add(id);
                getAllChildren(map, id, result);
            }
        }
    }
}



////////////////////////////////////
// HashMap + Breadth First Search //
////////////////////////////////////
public class Solution {

    /* The complexity is the same as the DFS */
    public List < Integer > killProcess(List < Integer > pid, List < Integer > ppid, int kill) {

        /* Same as the last method */
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                List<Integer> l = map.getOrDefault(ppid.get(i), new 
                ArrayList<>());
                l.add(pid.get(i));
                map.put(ppid.get(i), l);
            }
        }

        /* BFS */
        ArrayDeque<Integer> q = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        q.offer(kill);

        while (!q.isEmpty()) {
            int k = q.poll();
            result.add(k);
            if (map.containsKey(k)) {
                for (int child : map.get(k)) {
                    q.offer(child);
                }
            }
        }
        return result;
    }
}


