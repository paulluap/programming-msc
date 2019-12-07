package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses_22 {
    public static void main(String[] args) {
        GenerateParentheses_22 sol = new GenerateParentheses_22();
        System.out.println(sol.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        doGenerateParen(n*2, 0, 0, "", result);
        return result;
    }

    private void doGenerateParen(int n, int open, int close, String s, List<String> result){
        if (open > n/2) {
            //no hope
            return;
        }
        if (open + close == n){
//            System.out.println("done: "+ s + " - open: " + open + ", close: " + close + ", n: " + n);
            if (open == close) result.add(s);
            //done
            return;
        }
        if (close + 1 <= open){
            //we can both close or open
            doGenerateParen(n, open+1, close, s + "(", result);
            doGenerateParen(n, open, close+1, s + ")", result);
        }else{
            //we can only do open
            doGenerateParen(n, open+1, close, s + "(", result);
        }
    }

}
