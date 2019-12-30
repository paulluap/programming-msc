package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NQueens_51 {

    public static void main(String[] args) {
        NQueens_51 sol = new NQueens_51();
        System.out.println(sol.solveNQueens(4));

    }
    //place queen row by row,
    //n different ways to put in one column
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        solveNQueues(0, new int[n], n, result);
        return result;
    }

    private void solveNQueues(int row, int column[], int N, List<List<String>> result){
        /*
         * column index array, enough to keep our state
         * column[x] is the column index of row x
         */
        if (row == N){
            //reconstruct
            List<String> r = new ArrayList<>();
            for(int i=0; i<N; i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<N; j++){
                    if (column[i] == j) {
                        sb.append("Q");
                    }else{
                        sb.append(".");
                    }
                }
                r.add(sb.toString());
            }
            result.add(r);
            return;
        }
        for(int j=0; j<N; j++){
            if (isOK(column, row, j, N)) {
                column[row] = j;
                solveNQueues(row+1, column, N, result);
            }
        }
    }

    private boolean isOK(int column[], int i, int j, int N) {
        //go leftup, rightup, and up, to check if any attack
        int left = j-1, right = j+1;
        for(int r=i-1; r>=0; r--){
            //go up: i-1, j, and see q
            if (column[r] == j) return false;
            //go left up: i-1, j-1, and see q
            if (left >= 0 && column[r] == left--) return false;
            //go right up: i-1, j+1, and see q
            if (right < N && column[r] == right++) return false;
        }
        return true;
    }


}
