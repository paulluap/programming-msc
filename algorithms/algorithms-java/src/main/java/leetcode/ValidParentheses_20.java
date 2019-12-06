package leetcode;

import java.util.Stack;

public class ValidParentheses_20 {

    public static void main(String[] args) {
        ValidParentheses_20 sol = new ValidParentheses_20();
        System.out.println(sol.isValid("()"));
        System.out.println(sol.isValid("()[]{}"));
        System.out.println(sol.isValid("(]"));
        System.out.println(sol.isValid("["));
    }

    //{[]}
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) return false;
            Character top = stack.pop();
            if (c == ')' && top.equals('(')) continue;
            if (c == '}' && top.equals('{')) continue;
            if (c == ']' && top.equals('[')) continue;
            return false;
        }
        return stack.isEmpty();
    }
}
