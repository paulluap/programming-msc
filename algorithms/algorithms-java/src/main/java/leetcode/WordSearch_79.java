package leetcode;

import javax.security.auth.callback.TextInputCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * board =
 * [
 *   ['0','1','2','3'],
 *   ['4','5','6','7'],
 *   ['8','9','10','11']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 * row = 3
 * col = 4
 *
 * 10 : [10/4, 10%4], [2, 2]
 * 7 : [7/4, 7%4] : [1, 3]
 */

//TODO: not fast enough 18%
public class WordSearch_79 {
    public static void main(String[] args) {
        char[][] board = new char[][]{
            new char[]{'A','B','C','E'},
            new char[]{'S','F','C','S'},
            new char[]{'A','D','E','E'}
        };
        WordSearch_79 sol = new WordSearch_79();
        System.out.println(sol.exist(board, "ABCCED"));
        System.out.println(sol.exist(board, "SEE"));
        System.out.println(sol.exist(board, "ABCB"));

        board =  new char[][]{
            new char[] {'A','B','C','E'},
            new char[] {'S','F','E','S'},
            new char[] {'A','D','E','E'}
        };
        System.out.println(sol.exist(board, "ABCESEEEFS"));
    }

    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if (board[i][j] == word.charAt(0)){
                    boolean[][] marked = new boolean[board.length][board[i].length];
                    for(int r=0; r<marked.length; r++){
                        marked[r] = new boolean[board[i].length];
                    }
                    if (exist(board, i, j, 0, word, marked)) return true;
                }
            }
        }
        return false;
    }

    //board[i][j] must be word.charAt(d)
    private boolean exist(char[][] board, int i, int j, final int d, String word, boolean[][] marked){

        if (marked[i][j]) {
            return false;
        }

        char c = word.charAt(d);
        if (board[i][j] != c){
            return false;
        }

        if (d==word.length()-1){
            return true;
        }

        //mark the current step
        marked[i][j]=true;
        if (inRange(board, i, j-1) && exist(board, i, j-1, d+1, word, marked)) { return true; }
        if (inRange(board, i, j+1) && exist(board, i, j+1, d+1, word, marked)) { return true; }
        if (inRange(board, i-1, j) && exist(board, i-1, j, d+1, word, marked)) { return true; }
        if (inRange(board, i+1, j) && exist(board, i+1, j, d+1, word, marked)) { return true; }
        //if no luck, unmark the step for other opportunities
        marked[i][j] = false;
        return false;
    }

    private boolean inRange(char[][] board, int i, int j){
        int row = board.length;
        int col = board[0].length;
        return i>=0 && i<row && j>=0 && j<col;
    }
}
