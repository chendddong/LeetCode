/*
    A 2D grid map of m rows and n columns is initially filled with water. We may
    perform an addLand operation which turns the water at position 
    (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 */

/*
    Example:

    Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
    Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

    0 0 0
    0 0 0
    0 0 0
    Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

    1 0 0
    0 0 0   Number of islands = 1
    0 0 0
    Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

    1 1 0
    0 0 0   Number of islands = 1
    0 0 0
    Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

    1 1 0
    0 0 1   Number of islands = 2
    0 0 0
    Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

    1 1 0
    0 0 1   Number of islands = 3
    0 1 0
    We return the result as an array: [1, 1, 2, 3]

    Challenge:

    Can you do it in time complexity O(k log mn), where k is the length of the positions?

 */

public class Solution {
    /*  
        Other Approaches:

        BFS 
            1. traversal
                2. BFS
        
        DFS
            1. traversal
                2. DFS
                    
    ---------------------------------------------------------------------------    
    
        Actually, we need the number of the connecting block which is the
        UnionFind III template.
                
        Time after using the UF O(k) +  O(n * m)
        Why 1D array. Easy to use !!!

        Note that:
        
        We can't use UF when there is a deleting because there is information
        lost during the process.
        
    ---------------------------------------------------------------------------            
        When to use UF:
            1. About Union Set
            2. If in the Same Set.
    
        VERY VERY VERY IMPORTANT

        Dive deeply after and draw those things

    ---------------------------------------------------------------------------                
     */
    int convertToId(int x, int y, int m) {
        return m * x + y;
    }
    class UnionFind {
        HashMap<Integer, Integer> father = new HashMap<>();
        UnionFind(int n, int m) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int id = convertToId(i, j, m);
                    father.put(id, id);
                }
            }
        }
        int compressedFind(int x) {
            /* Bit tricky tho */
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }
            int fa = x;
            int temp = Integer.MIN_VALUE; /* Since the fathers are positive */
            /* 
                Get each X's temp parent and update them to the parent we just
                found and this is where the compression happens.
             */
            while (fa != father.get(fa)) {
                temp = father.get(fa);
                /* Tricky part */
                father.put(fa, parent);
                fa = temp;
            }
            
            return parent;
        }
        void union(int x, int y) {
            int parentX = compressedFind(x);
            int parentY = compressedFind(y);
            if (parentX != parentY) {
                father.put(parentX, parentY);
            }
        }
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        /* Edge */   
        List<Integer> result = new ArrayList<>();
        if (positions == null) {
            return result;
        }

        /* Initializing */
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};        
        int[][] island = new int[m][n];
        UnionFind uf = new UnionFind(m, n);
        int count = 0;

        /* Traverse the operator */
        for (int[] pair : positions) {
            int x = pair[0];
            int y = pair[1];

            /* The point is 0 */
            if (island[x][y] != 1) {
                count++;
                island[x][y] = 1;
                int id = convertToId(x, y, n);
                /* 4 adjacent points; Looking up to dx[] and dy[] */
                for (int j = 0; j < 4; j++) {
                    int adjX = x + dx[j];
                    int adjY = y + dy[j];
                    /* See if the point is valid and 1 too */
                    if (0 <= adjX && adjX < m &&
                        0 <= adjY && adjY < n &&
                        island[adjX][adjY] == 1) {
                        int adjID = convertToId(adjX, adjY, n);

                        /* Query */
                        int idFather = uf.compressedFind(id);
                        int adjIDfather = uf.compressedFind(adjID);
                        if (idFather != adjIDfather) {
                            count--;
                            uf.union(id, adjID);
                        }

                    }
                }
            }
            result.add(count);
        }
        return result;
    }
}