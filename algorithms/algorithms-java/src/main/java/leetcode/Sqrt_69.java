package leetcode;

public class Sqrt_69 {
    public int mySqrt(int x) {
        double guess = doMySqrt(x);
        long floor = (long) guess;
        long ceil = floor + 1;
        if (ceil*ceil > x) return (int)floor;
        return (int)ceil;
    }

    public double doMySqrt(int x) {
        double lo = 0, hi = x;
        while(true){
            double guess = lo + (hi - lo) / 2;
            double guessSquare = guess * guess;
            System.out.println("guess: " + guess);
            if (Math.abs(guessSquare - x) < 0.5) return guess;

            if (guessSquare > x) hi = guess;
            else if (guessSquare < x ) lo = guess;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Sqrt_69().mySqrt(9));
//        System.out.println(new Sqrt_69().mySqrt(8));
        System.out.println(new Sqrt_69().mySqrt(2147483647));
    }
}
