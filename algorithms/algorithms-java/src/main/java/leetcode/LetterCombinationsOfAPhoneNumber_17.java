package leetcode;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber_17 {

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfAPhoneNumber_17().letterCombinations("234"));
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return Collections.emptyList();
        HashMap<String, String> keyboard = new HashMap<String,String>();
        keyboard.put("2", "abc");
        keyboard.put("3", "def");
        keyboard.put("4", "ghi");
        keyboard.put("5", "jkl");
        keyboard.put("6", "mno");
        keyboard.put("7", "pqrs");
        keyboard.put("8", "tuv");
        keyboard.put("9", "wxyz");

        List<String> result = new ArrayList<>();
        computeCombination(keyboard, digits, "", result);
        return result;

    }

    private void computeCombination(Map<String, String> keyboard, String digits, String partial, List<String> result) {
        if (digits.isEmpty()) {
            result.add(partial);
            return;
        };
        String number = digits.substring(0, 1);
        for(char c : keyboard.get(number).toCharArray()){
            computeCombination(keyboard, digits.substring(1),partial+c, result);
        }
    }

}
