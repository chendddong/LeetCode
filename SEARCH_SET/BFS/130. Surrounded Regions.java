/////////
// BFS //
/////////

public class Solution {
    static final int[] directionX = {1, -1, 0, 0};
    static final int[] directionY = {0, 0, 1, -1};

    static final char FREE = 'F';
    static final char TRAVELED = 'T';

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;

        /* Traverse the boarders and set those 'O' to FREE */ 
        for (int i = 0; i < row; i++) {
            bfs(board, i, 0);
            bfs(board, i, col - 1);
        }
        for (int j = 1; j < col - 1; j++) {
            bfs(board, 0, j);
            bfs(board, row - 1, j);
        }

        /* Setting up the values */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch(board[i][j]) {
                    case 'O': /* Set the 'O' to 'X' */
                        board[i][j] = 'X';
                        break;
                    /* Keep those 'O's who can reach out the boarders */
                    case 'F':
                        board[i][j] = 'O'; 
                }
            }
        }
    }

    public void bfs(char[][] board, int i, int j) {
        if (board[i][j] != 'O') {
            return;
        }
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(i, j));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            board[cur.x][cur.y] = FREE;
            /* For every neighbor, we choose the right ones to expand */
            for (Node node : expand(board, cur)) {
                q.offer(node);
            }
        }
    }

    private List<Node> expand(char[][] board, Node node) {
        List<Node> expansion = new ArrayList<>();

        for (int i = 0; i < directionX.length; i++) {
            int x = node.x + directionX[i];
            int y = node.y + directionY[i];

            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length
                && board[x][y] == 'O') {
                board[x][y] = TRAVELED;
                expansion.add(new Node(x, y));
            }
        }

        return expansion;
    }
}
