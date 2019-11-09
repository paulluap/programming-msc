package cases;

import datastructure.*;

/**
 * evaluate a fully parenthesized arithmetic expression
 * from left ot right,
 * push operands onto the operand stack
 * push operators onto the operator stack
 * ignore left parentheses
 * on encountering right parentheses, pop an operator, pop the requisiste number of operands, and push onto the operand stack
 * the result of applying that operator to those operands
 *
 */
public class ArithmeticExprEval {
    public static void main(String[] args) {
        String expr = "( 1+((2+38)*(4*  5) ))";
        System.out.println(expr);
        ExprLexer lexer = new ExprLexer(expr);
        StringBuilder sb = new StringBuilder();

        ResizingArrayStack<String> ops = new ResizingArrayStack<String>();
        ResizingArrayStack<Integer> vals = new ResizingArrayStack<Integer>();

        //TODO: add sqrt operator
        while(lexer.hasNext()  ){
            String token = lexer.nextToken();
            if (token.equals("(")) { /*ignore left */}
            else if (token.equals("+")) ops.push(token);
            else if (token.equals("-")) ops.push(token);
            else if (token.equals("*")) ops.push(token);
            else if (token.equals("/")) ops.push(token);
            else if (token.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                if (op.equals("-")) vals.push(vals.pop() - vals.pop());
                if (op.equals("*")) vals.push(vals.pop() * vals.pop());
                if (op.equals("/")) vals.push(vals.pop() / vals.pop());
            }else{
                vals.push(Integer.parseInt(token));
            }
        }
        System.out.println("expression: " + expr + " = " + vals.pop());
    }

    private static int eval(String expr) {
        return 0;
    }

    static private class ExprLexer {

        private String expr;
        private int position;
        private char current;

        ExprLexer(String expr){
            this.expr = expr;
            this.position = 0;
            this.current = expr.charAt(this.position);
        }

        private void advance() {
            this.position ++;
            if (this.position < this.expr.length()) {
                this.current = expr.charAt(this.position);
            }else{
                this.current = '\0';
            }
        }

        public boolean hasNext(){
            return this.position < expr.length() ;
        }

        private void skipWhitespace(){
            while(currentIsSpace() && hasNext()) {
                advance();
            }
        }

        private boolean currentIsSpace(){
            return  this.current == ' ';
        }

        private boolean currentIsDigit(){
            return this.current == '0' ||
                   this.current == '1' ||
                   this.current == '2' ||
                   this.current == '3' ||
                   this.current == '4' ||
                   this.current == '5' ||
                   this.current == '6' ||
                   this.current == '7' ||
                   this.current == '8' ||
                   this.current == '9';
        }

        //eats an integer and stops at the next one
        private String eatInteger(){
            StringBuilder result = new StringBuilder();
            while(currentIsDigit()) {
                result.append(current);
                advance();
            }
            return result.toString();
        }

        private String eatOne(){
            String result = current + "";
            advance();
            return result;
        }

        private boolean currentIsOperator(){
            return  (current == '+')||
                    (current == '-')||
                    (current == '*')||
                    (current == '/')||
                    (current == '(')||
                    (current == ')');
        }

        public String nextToken(){
            while(hasNext()){
                if(currentIsSpace())        skipWhitespace();
                else if(currentIsDigit())    return eatInteger();
                else if(currentIsOperator()) return eatOne();
                else { throw new RuntimeException("unknow character: " + current); }
            }
            return "EOF";
        }


    }
}
