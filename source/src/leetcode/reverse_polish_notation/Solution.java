package leetcode.reverse_polish_notation;

import java.util.HashSet;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        String[] input = new String[]{"4","13","5","/","+"};
        evalRPN(input);
    }

    public static int evalRPN(String[] tokens) {
        final String plus = "+";
        final String minus = "-";
        final String div = "/";
        final String multi = "*";

        int num1 = 0;
        int num2 = 0;

        HashSet<String> op = new HashSet<>(4, 1f);
        op.add(plus);
        op.add(minus);
        op.add(div);
        op.add(multi);

        Stack<Integer> tokenStack = new Stack<>();

        for (String token : tokens) {
            if (!tokenStack.isEmpty() && op.contains(token)) {
                num2 = tokenStack.pop();
                num1 = tokenStack.pop();
                switch (token) {
                    case plus:
                        tokenStack.push(num1 + num2);
                        break;
                    case minus:
                        tokenStack.push(num1 - num2);
                        break;
                    case div:
                        tokenStack.push(num1 / num2);
                        break;
                    case multi:
                        tokenStack.push(num1 * num2);
                        break;
                }
            } else {
                tokenStack.push(Integer.valueOf(token));
            }
        }

        return tokenStack.pop();
    }
}
