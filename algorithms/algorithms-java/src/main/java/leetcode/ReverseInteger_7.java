package leetcode;

public class ReverseInteger_7 {
    public static void main(String[] args) {
        ReverseInteger_7 solution = new ReverseInteger_7();
        System.out.println(solution.reverse(1534236469));
        System.out.println(Integer.MIN_VALUE%10);
    }
    public int reverse(int x) {
        int n = x;
        int result = 0;
        while(n != 0) {
            int r = n%10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE/10 && r > Integer.MAX_VALUE%10)) return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE/10 && r < Integer.MIN_VALUE%10)) return 0;
            result = result*10 + r;
            n = n/10;
        }
        return result;
    }
}
