package leetcode;

public class ZigZagConversion_6 {

    public static void main(String[] args) {
        ZigZagConversion_6 solution = new ZigZagConversion_6();
        System.out.println(solution.convert("PAYPALISHIRING", 3));//PAHNAPLSIIGYIR
        System.out.println(solution.convert("PAYPALISHIRING", 4));//PAHNAPLSIIGYIR
        System.out.println(solution.convert("A", 2));//PAHNAPLSIIGYIR
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        int N = s.length();
        int maxInc = (numRows - 1) * 2;
        StringBuilder sb = new StringBuilder();

        for (int r = 0, inc = maxInc; r < numRows; r++, inc-=2) {
            if (inc == 0) inc = maxInc;

            for(int c=r, x=0; c<N; x++){
                sb.append(s.charAt(c));
                if (inc == maxInc) c += inc;
                //alternating inc
                else if (x%2==0)   c += inc;
                else               c += (maxInc - inc);
            }
        }

        return sb.toString();
    }

    //simulate using index

    /**
     * observation based implementation
     *
     * 0 2 4
     * 1 3 5
     *
     * 0     4     8
     * 1  3  5  7  9
     * 2     6
     *
     *
     * 0       8        16
     * 1     7 9      15
     * 2   6   10   14
     * 3 5     11 13
     * 4       12
     *
     * row 0 : 8
     * row 1 : 6 - 2
     * row 2 : 4 - 4
     * row 3 : 2 - 6
     * row 4 : 8
     */

}
/**
 * Runtime: 3 ms, faster than 96.41% of Java online submissions for ZigZag Conversion.
 * Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for ZigZag
 */
