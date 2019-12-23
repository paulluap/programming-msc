package leetcode;

//TODO: too slow
public class WordDictionary_211 {

    //let's build a tries
    static class Node {
        char c;
        boolean isWord;
        Node left, right, mid;
    }

    private Node root;
    /** Initialize your data structure here. */
    public WordDictionary_211() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        root = addWord(root, word, 0);
    }

    private Node addWord(Node node, String word, int d){
        char c = word.charAt(d);
        if (node == null){
            node = new Node();
            node.c = c;
        }
        if (c > node.c ) {
            node.right = addWord(node.right, word, d);
        }else if(c < node.c){
            node.left = addWord(node.left, word, d);
        }else if(d < word.length()-1){
            node.mid = addWord(node.mid, word, d+1);
        }else{
            //eq and reaches the end
            node.isWord = true;
        }

        return node;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    public boolean search(Node x, String word, int d){
        if (x == null) return false;

        char c = word.charAt(d);
        if (c == '.'){
            if (d==word.length()-1) {
                return x.isWord;
            }
            return search(x.left, word, d) || search(x.right, word, d) || search(x.mid, word, d+1);
        }

        if (c < x.c) {
            return search(x.left, word, d);
        }else if (c> x.c){
            return search(x.right, word, d);
        }else if (d<word.length()-1){
            return search(x.mid, word, d+1);
        }else {
            return x.isWord;
        }
    }

    public static void main(String[] args) {
        int i = 'z';
        System.out.println(i);
        WordDictionary_211 dic = new WordDictionary_211();

        dic.addWord("ab");

        System.out.println(dic.search(".a"));
        System.out.println(dic.search("a."));
    }
}
