package leetcode;

public class PalindromNumber_9 {
    public static void main(String[] args) {
        PalindromNumber_9 solution = new PalindromNumber_9();
        System.out.println(solution.isPalindrome(11)); //true
        System.out.println(solution.isPalindrome(121)); //true
        System.out.println(solution.isPalindrome(1213)); //false
        System.out.println(solution.isPalindrome(1)); //true
        System.out.println(solution.isPalindrome(0)); //true

    }

    public boolean isPalindrome(int x) {
        if (x<0) return false;
        //determine no of digits
        int d = 0;
        int x1 = x;
        while(x1 > 0) {
            x1 /= 10;
            d ++;
        }
        int mid = d/2;
        int rightRev = 0;
        for(int i=0; i<mid; i++){
            rightRev = rightRev*10 + x%10;
            x /= 10;
        }
        if (rightRev == x) return true;
        if (d - mid > mid && rightRev == x/10) return true;

        return false;
    }
}
