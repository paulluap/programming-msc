package leetcode;

public class IntegerToRoman_12 {

    public static void main(String[] args) {
        IntegerToRoman_12 solution = new IntegerToRoman_12();
        System.out.println(solution.intToRoman(58).equals("LVIII"));
        System.out.println(solution.intToRoman(3).equals("III"));
        System.out.println(solution.intToRoman(4).equals("IV"));
        System.out.println(solution.intToRoman(9).equals("IX"));
        System.out.println(solution.intToRoman(1994).equals("MCMXCIV"));
    }


    public String intToRoman(int num) {
        int[] steps = new int[]        {1,   4,   5,  9  , 10,  40,  50, 90, 100, 400, 500, 900, 1000};
        String[] symbols = new String[]{"I","IV","V","IX", "X","XL","L","XC","C", "CD", "D", "CM", "M"};
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            int diff = Integer.MAX_VALUE, step = -1;
            for(int i=0; i<steps.length; i++){
                int currentDiff = num - steps[i];
                if (currentDiff < 0) break;
                if (currentDiff < diff) {
                    diff = currentDiff;
                    step = i;
                }
            }
            sb.append(symbols[step]);
            num -= steps[step];
        }
        return sb.toString();
    }
}
