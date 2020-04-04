package leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MaximalSpace_221 {

    public static void main(String[] args) {
        MaximalSpace_221 sol = new MaximalSpace_221();
//        char[][] input = {
//            { '1', '0', '1', '0', '0' },
//            { '1', '0', '1', '1', '1' },
//            { '1', '1', '1', '1', '1' },
//            { '1', '0', '0', '1', '0' }
//        };
//        char[][] input = {
//                { '0', '1', '1', '1', '0' },
//                { '1', '1', '1', '1', '0' },
//                { '0', '1', '1', '1', '1' },
//                { '0', '1', '1', '1', '1' },
//                { '0', '0', '1', '1', '1' },
//        };
//        char[][] input = {{'1'}};
//        char[][] input = {{'1','1'},{'1','1'}};
        char[][] input = {{'1','0'},{'0','1'},{'0','1'},{'0','1'},{'1','1'},{'0','0'},{'0','1'}};

        int output = sol.maximalSquare(input);
        System.out.println(output);
    }


    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 ) {
            return 0;
        }
        int dp[] = new int[matrix[0].length];
        int max = 0;
        for(int i=0; i<matrix.length; i++){
            int prev = 0;
            for (int j=0; j<matrix[i].length; j++){
                int dpJSnapShot = dp[j];
                if (i==0 || j==0) {
                    dp[j] = matrix[i][j] == '1' ? 1 : 0;
                }else if ( matrix[i][j] == '1' ) {
                    dp[j] = Math.min(prev /*left, upper*/, Math.min(dp[j] /* up */, dp[j-1] /*left*/)) + 1;
                }else{
                    dp[j] = 0;
                }

                max = Math.max(dp[j], max);
                prev = dpJSnapShot;
            }
        }
        return max * max;
    }

}
