/*
    For a undirected graph with tree characteristics, we can choose any node as
    the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 */

/*
    Format
    The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

    Example 1:

    Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

            0
            |
            1
           / \
          2   3
    return [1]

    Example 2:

    Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

         0  1  2
          \ | /
            3
            |
            4
            |
            5
    return [3, 4]

    Note:

    (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

    (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

    Classic Using degree topological sort!!!
 */

public class Solution {
    /*
        1. We should first compute the degree of every node.
        2. Put the nodes with degree 1 into the list or queue.
        3. Remove them from the neighbor and then calculate the nodes with
        degree 1. And the remaining node are the new root.

        Time Complexity - O(n)， Space Complexity - O(n)
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> leaves = new ArrayList<>();
        if(n <= 1) {
            return Collections.singletonList(0);
        }
        /* list of edges to  Adjacency Lists */
        Map<Integer, Set<Integer>> graph = new HashMap<>();     
        
        for(int i = 0; i < n; i++) { /* Setting up */
            graph.put(i, new HashSet<Integer>());
        }

        for(int[] edge : edges) { /* Add nodes */
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        for(int i = 0; i < n; i++) {
            if(graph.get(i).size() == 1) {
                leaves.add(i); /* Use leaves as a queue */
            }
        }
        
        while(n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for(int leaf : leaves) {
                for(int newLeaf : graph.get(leaf)) {
                    /* Remove first from graph and see if the degree is 1 */
                    graph.get(leaf).remove(newLeaf);
                    graph.get(newLeaf).remove(leaf);
                    if(graph.get(newLeaf).size() == 1) {
                        newLeaves.add(newLeaf);
                    }
                }
            }
            leaves = newLeaves; /* Change the leaves(next level) */
        }
        
        return leaves;
    }
}
