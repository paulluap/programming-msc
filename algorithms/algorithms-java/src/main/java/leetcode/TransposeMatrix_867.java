package leetcode;

public class TransposeMatrix_867 {
    /**
     *
     * [[1,2,3]]
     * [[4,5,6]]
     * [[7,8,9]]
     *
     */
    public int[][] transpose(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int TR = C, TC = R;
        int[][] T = new int[TR][TC];
        for(int r=0; r<TR; r++){
            T[r] = new int[TC];
        }
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                T[c][r] = A[r][c];
            }
        }
        return T;
    }
}
