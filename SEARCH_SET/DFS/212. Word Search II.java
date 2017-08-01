/*
    Given a 2D board and a list of words from the dictionary, find all words in
    the board.
 */

/*
    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

    For example,
    Given words = ["oath","pea","eat","rain"] and board =

    [
      ['o','a','a','n'],
      ['e','t','a','e'],
      ['i','h','k','r'],
      ['i','f','l','v']
    ]
    Return ["eat","oath"].
    Note:
    You may assume that all inputs are consist of lowercase letters a-z.

 */

/*
    Very Very Very Important:
    
    Just draw the search tree when we are doing the search
    We can do this just by using the DFS, first.

    Then we can transfer the code by using the Trie because the Trie data
    Structure can prune the branches.        
 */

/////////
// DFS //               TLE in LintCode
/////////

public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }    
        /* Traverse each words and see if it exists */
        for (String word : words) {
            if (exists(board, word)) {
                if (!result.contains(word)) {
                    result.add(word);                    
                }
                
            }
        }
        return result;
    }

    private int m; /* Col */
    private int n; /* Row */
    private boolean exists(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        if (word.length() == 0) {
            return true;
        }
        m = board.length;
        n = board[0].length;
        int wordIndex = 0;

        /* Traverse the board see which position matches the first c of word */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char wc = word.charAt(wordIndex);
                char wb = board[i][j];
                if (wc == wb) {
                    boolean found = dfs(board, word, wordIndex, i, j);
                    if (found) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, 
                        String word, 
                        int wordIndex, 
                        int i,  
                        int j) {
        if (word.length() == wordIndex) {
            return true;
        }

        if (i < 0 || i >= m || j < 0 || j >= n || word.charAt(wordIndex) != board[i][j]) {
            return false;
        }

        board[i][j] ^= 256; /* Mark as visited (not a valid ch) */
        /* Go for the neighbors */
        boolean res = dfs(board, word, wordIndex + 1, i, j + 1) ||
                      dfs(board, word, wordIndex + 1, i - 1, j) ||
                      dfs(board, word, wordIndex + 1, i, j - 1) ||
                      dfs(board, word, wordIndex + 1, i + 1, j);
        board[i][j] ^= 256;
        return res;
    }
}
