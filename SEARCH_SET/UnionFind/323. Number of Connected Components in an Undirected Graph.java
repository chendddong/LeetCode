/*
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
    edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
 */

/*
    Example 1:
         0          3
         |          |
         1 --- 2    4
    Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

    Example 2:
         0           4
         |           |
         1 --- 2 --- 3
    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

    Note:
    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


 */

////////////////////////
// StraightForward UF //
////////////////////////


/* We can also use DFS or BFS to solve the problem*/
public class Solution {
    HashMap<Integer, Integer> father;
    class UnionFind {
        UnionFind(int n) {
            father = new HashMap<>();
            for (int i = 0; i < n; i++) {
                father.put(i, i);
            }
        }
        int find(int x) {
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }

            int temp = -1;
            int fa = father.get(x);

            while (fa != father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }

            return parent;
        }
        void union(int x, int y) {
            int faX = find(x);
            int faY = find(y);

            if (faX != faY) {
                father.put(faX, faY);
            }
        }        
    }

    public int countComponents(int n, int[][] edges) {
        int count = n;
        UnionFind uf = new UnionFind(n);
        
        for (int[] pair : edges) {
            int fone = uf.find(pair[0]);
            int ftwo = uf.find(pair[1]);
            
            if (fone != ftwo) {
                uf.union(pair[0], pair[1]);
                count--;
            }
        }
        
        return count;
    }
}



