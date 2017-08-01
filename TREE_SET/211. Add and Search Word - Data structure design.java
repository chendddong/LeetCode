/**
 * Design a data structure that supports the following two operations:
 * 
 */

/*
    void addWord(word)
    bool search(word)
    search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

    For example:

    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true
    Note:
    You may assume that all words are consist of lowercase letters a-z.
 */

/*  
    What can appear in interview in terms of Trie:
        1. Implement the data structure
        2. The Something pertaining the Prefix
        3. DFS + TRIE(Most important one)
*/

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

///////////////////
// Array of Trie //
///////////////////

class TrieNode {
    public TrieNode[] children;
    public boolean hasWord;
    public TrieNode() { /* Assign the initialized children to null */
        children = new TrieNode[26];
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
        hasWord = false;
    }
}

public class WordDictionary {
    /* We need a root to manipulate the whole tree */
    private TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    /* Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        int pos;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            /* Put offset in the array */
            pos = wordArray[i] - 'a';
            if (cur.children[pos] == null) {
                cur.children[pos] = new TrieNode();
            }
            cur = cur.children[pos]; /* Go deeper */
        }    
        cur.hasWord = true;
    }

    /* 
        Has been modified comparing to the original template since we have to
        care about the regex thing.
    */
    public boolean find(String word, int index, TrieNode cur) {
        if (index == word.length()) {
            return cur.hasWord;
        }
        Character c = word.charAt(index);
        if (c == '.') { /* Only go to the children where they are not null */
            for (int i = 0; i < 26; i++) {
                if (cur.children[i] != null) {
                    if (find(word, index + 1, cur.children[i])){
                        return true;
                    }
                }
            }
            return false;
        } else if (cur.children[c - 'a'] != null) { /* Go deeper */
            return find(word, index + 1, cur.children[c - 'a']);
        } else {
            /* cur.children[c - 'a'] == null */
            return false;
        }
        
        
    }
    /* 
        Returns if the word is in the data structure. A word could contain the
        dot character '.' to represent any one letter. 
     */
    public boolean search(String word) {
        /* Search from the root */
        return find(word, 0, root);
    }
}

///////////////////
// HashMap + DFS //
///////////////////

/* TrieNode is much easier to construct */
class TrieNode {
    public HashMap<Character, TrieNode> children;
    public boolean hasWord;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        hasWord = false;
    }
}

public class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cur = root;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (!cur.children.containsKey(c)) { /* No previous occurrence */
                TrieNode newNode = new TrieNode();
                cur.children.put(c, newNode);
            }
            /* Found the child; Just use get(c) to go deep */
            cur = cur.children.get(c);
        }
        cur.hasWord = true;
    }

    public boolean find(String word, int index, TrieNode cur) {
        if (index == word.length()) {
            if (cur.children.size() == 0) {
                return true;
            } 
            return false;
            
        }

        Character c = word.charAt(index);
        if (cur.children.containsKey(c)) {
            if (index == word.length() - 1 && cur.children.get(c).hasWord) {
                return true;
            }
            return find(word, index + 1, cur.children.get(c));
        } else if (c == '.') {
            boolean result = false;
            for (Map.Entry<Character, TrieNode> child : cur.children.entrySet())
            {
                if (index == word.length() - 1 && child.getValue().hasWord) {
                    return true;
                }
                /* If any path is true, set result to be true */
                if (find(word, index + 1, child.getValue())) {
                    result = true;
                }
            }
            return result;
        } else {
            return false;
        } 
    }

    public boolean search(String word) {
        return find(word, 0, root);
    }
}


//version 3: use HashMap and bfs
class TrieNode{
    public Map<Character,TrieNode> children;
    public boolean hasWord;
    public TrieNode(){
        children=new HashMap<>();
        hasWord=false;
    }
}

public class WordDictionary {
    TrieNode root;
    public WordDictionary(){
        root=new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        // Write your code here
        TrieNode cur=root;
        for(int i=0;i<word.length();++i){
            char c=word.charAt(i);
            TrieNode nextNode=cur.children.get(c);
            if(nextNode==null){
                nextNode=new TrieNode();
                cur.children.put(c,nextNode);
            }
            cur=nextNode;
        }
        cur.hasWord=true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Write your code here
        Queue<TrieNode> nexts=new LinkedList<>();
        nexts.add(root);
        int index=0;
        while(!nexts.isEmpty()){
            int size=nexts.size();
            char c=word.charAt(index);
            boolean flag=false;
            for(int i=0;i<size;++i){
                TrieNode cur=nexts.poll();
                if(c=='.'){
                    for(TrieNode tempNode:cur.children.values()){
                        nexts.add(tempNode);
                        flag|=tempNode.hasWord;
                    }
                } else if(cur.children.containsKey(c)){
                    TrieNode nextNode=cur.children.get(c);
                    flag|=nextNode.hasWord;
                    nexts.add(nextNode);
                }
            }
            index++;
            if(index>=word.length()) return flag;
        }
        return false;
    }
}
