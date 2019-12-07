package leetcode;

import java.util.function.Function;

public class StringToInteger_8 {

    public static void main(String[] args) {
        StringToInteger_8 solution = new StringToInteger_8();
        System.out.println(solution.myAtoi(" +89238 asdfasdf"));
        System.out.println(solution.myAtoi(" +92134 asdfasdf"));
        System.out.println(solution.myAtoi(" 89238 asdfasdf"));
        System.out.println(solution.myAtoi(" - 89238 asdfasdf"));
        System.out.println(solution.myAtoi(" -8 asdfasdf"));
        System.out.println(solution.myAtoi("+"));
        System.out.println(solution.myAtoi("2147483648"));
    }

    public int myAtoi(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++){
            //ignore white space
            if (str.charAt(i) ==' ') continue;

            int j = consumeSign(sb, str, i);
            if (j>i){ //consumed sign
                //must be followed by digit
                int k = consumeDigit(sb, str, j);
                //not digit following sign
                if (k == j) sb.delete(0,1);
                break;
            }

            consumeDigit(sb, str, j);
            break;
        }
        if (sb.length()==0) return 0;

        int diff = 0 - '0';
        boolean isMinus = sb.charAt(0) == '-';
        int result = 0;

        for(int i=0; i<sb.length(); i++){
            if (sb.charAt(i) == '+' || sb.charAt(i) == '-') continue;

            int last = sb.charAt(i) + diff;
            if (isMinus) last = -last;

            if (!isMinus)
            if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && last > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }

            if (isMinus)
            if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && last < Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }

            result = result*10 + last;
        }

        return result;
    }

    private int consumeSign(StringBuilder sb, String str, int i) {
        if (i>=str.length()) return i;

        char c = str.charAt(i);
        if (c == '+' || c == '-') {
            sb.append(c);
            return i+1;
        }
        return i;
    }

    private int consumeDigit(StringBuilder sb, String str, int i) {
        if (i>=str.length()) return i;

        char c = str.charAt(i);
        if (c >= '0' && c <= '9'){
            //consume digit
            sb.append(c);
            int j=i+1;
            while(j<str.length()){
                c = str.charAt(j);

                if (c >= '0' && c <= '9') sb.append(c);
                else break;

                j++;
            }
            return j;
        }
        return i;
    }


}
