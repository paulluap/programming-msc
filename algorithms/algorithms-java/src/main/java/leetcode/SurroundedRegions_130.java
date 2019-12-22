package leetcode;

import datastructure.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

//TODO: runtime too slow
public class SurroundedRegions_130 {
    public static void main(String[] args) {
        /**
         * [["X","O","X","X"],["O","X","O","X"],["X","O","X","O"],["O","X","O","X"],["X","O","X","O"],["O","X","O","X"]]
         */
        char[][] board = new char[][]{
                new char[]{'X', 'O', 'X', 'X'},
                new char[]{'O', 'X', 'O', 'X'},
                new char[]{'X', 'O', 'X', 'O'},
                new char[]{'O', 'X', 'O', 'X'},
                new char[]{'X', 'O', 'X', 'O'},
                new char[]{'O', 'X', 'O', 'X'},
        };
        board = new char[][]{
                new char[]{'O', 'O'},
                new char[]{'O', 'O'}
        };

        /**
         * [["O","O","O","O","X","X"],["O","O","O","O","O","O"],["O","X","O","X","O","O"],["O","X","O","O","X","O"],["O","X","O","X","O","O"],["O","X","O","O","O","O"]]
         */
        board = new char[][]{
                new char[]{'O','O','O','O','X','X'},
                new char[]{'O','O','O','O','O','O'},
                new char[]{'O','X','O','X','O','O'},
                new char[]{'O','X','O','O','X','O'},
                new char[]{'O','X','O','X','O','O'},
                new char[]{'O','X','O','O','O','O'},
        };
        //expected
        /**
         *
         * [["O","O","O","O","X","X"],
         *  ["O","O","O","O","O","O"],
         *  ["O","X","O","X","O","O"],
         *  ["O","X","O","O","X","O"],
         *  ["O","X","O","X","O","O"],
         *  ["O","X","O","O","O","O"]]
         */

        /**
         *
         [["O","X","X","O","X"],["X","O","O","X","O"],["X","O","X","O","X"],["O","X","O","O","O"],["X","X","O","X","O"]]
         */
        board = new char[][]{
                new char[]{'O','X','X','O','X'},
                new char[]{'X','O','O','X','O'},
                new char[]{'X','O','X','O','X'},
                new char[]{'O','X','O','O','O'},
                new char[]{'X','X','O','X','O'}
        };
        /**
         * expected
         * [["O","X","X","O","X"],
         *  ["X","X","X","X","O"],
         *  ["X","X","X","O","X"],
         *  ["O","X","O","O","O"],
         *  ["X","X","O","X","O"]]
         */
        new SurroundedRegions_130().solve(board);
        printBoard(board);
    }


    static void printBoard(char[][] board){
        for(char[] row : board)
            System.out.println(CollectionUtils.toString(row));
    }

    public void solve(char[][] board) {
        if (board.length==0) return;
        int R = board.length, C = board[0].length;
        int[] uf = new int[R*C];
        int[] weight = new int[R*C];

        for(int i=0; i<R*C; i++){
            uf[i] = i;
            weight[i] = 1;
        }

        for(int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if(i<R-1 && board[i][j]=='O' && board[i+1][j] == 'O') union(uf, weight, flat(C,i,j), flat(C, i+1, j));
                if(j<C-1 && board[i][j]=='O' && board[i][j+1] =='O') union(uf, weight, flat(C,i,j), flat(C, i, j+1));
            }
        }

        Set<Integer> boardComponents = new HashSet<>();

        for(int i=0; i<R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != 'O') continue;
                if (i==R-1 || i == 0 || j== C-1 || j == 0){
                    int c = find(uf, flat(C, i, j));
                    boardComponents.add(c);
                }
            }
        }

        for(int i=0; i<R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != 'O') continue;
                if (boardComponents.contains( find(uf, flat(C, i, j)))) continue;
                board[i][j] = 'X';
            }
        }
    }


    private void union(int[] uf, int[] weight, int i , int j){
        int iroot = find(uf,i);
        int jroot = find(uf,j);
        if (iroot == jroot) return;

        if (weight[iroot] < weight[jroot]){
            uf[iroot] = jroot;
            weight[jroot] = weight[iroot] + weight[jroot];
        }else{
            uf[jroot] = iroot;
            weight[iroot] = weight[iroot] + weight[jroot];
        }
    }

    private int find(int[] uf, int i){
        while (uf[i] != i) {
            i = uf[i];
        }
        return i;
    }

    private boolean isUnion(int[] uf, int i, int j){
        return find(uf, i)  == find(uf, j);
    }


    private int flat(int c, int i, int j){
        return i*c + j;
    }
}

