package leetcode;

public class RomanToInteger_13 {

    public static void main(String[] args) {
        System.out.println(new RomanToInteger_13().romanToInt("III"));
        System.out.println(new RomanToInteger_13().romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {
        int[] steps = new int[]        {1,   4,   5,  9  , 10,  40,  50, 90, 100, 400, 500, 900, 1000};
        String[] symbols = new String[]{"I","IV","V","IX", "X","XL","L","XC","C", "CD", "D", "CM", "M"};

        int result = 0;

        while(s.length() > 0) {
            int step = -1;
            for(int i=0; i<symbols.length; i++){
                if (s.startsWith(symbols[i])){
                    step = i;
                    if (symbols[i].length() == 2) break;
                }
            }
            s = s.substring(symbols[step].length());
            result += steps[step];
        }

        return result;
    }
}
