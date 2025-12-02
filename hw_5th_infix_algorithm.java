import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class hw_5th_infix_algorithm {
    private static int operatorPriority(String operator) {
        return switch (operator) {
            case "+" -> 1;
            case "-" -> 1;
            case "*" -> 2;
            case "/" -> 2;
            default -> 0;
        };
    }
    private static int countInfix(String[] calculate) {
        Stack<Integer> operand = new Stack<>();
        Stack<String> operator = new Stack<>();
        Set<String> operatorsSet = new HashSet<>(Set.of("+", "-", "*", "/", "(", ")"));
        for (String index : calculate) {
            if (!operatorsSet.contains(index)) {
                operand.push(Integer.valueOf(index));
            } else if (index.equals(")")) {
                while(!operator.isEmpty() && !operator.peek().equals("(")) {
                    int cache = operand.pop();
                    switch (operator.pop()) {
                        case "+" -> operand.push(operand.pop() + cache);
                        case "-" -> operand.push(operand.pop() - cache);
                        case "*" -> operand.push(operand.pop() * cache);
                        case "/" -> operand.push(operand.pop() / cache);
                    }
                }
                operator.pop();
            } else {
                if(!operator.isEmpty()) {
                    if (operatorPriority(operator.peek()) >= operatorPriority(index) && !index.equals("(")) {
                        int cache = operand.pop();
                        switch (operator.pop()) {
                            case "+" -> operand.push(operand.pop() + cache);
                            case "-" -> operand.push(operand.pop() - cache);
                            case "*" -> operand.push(operand.pop() * cache);
                            case "/" -> operand.push(operand.pop() / cache);
                        }
                    }
                }
                operator.push(index);
            }
        }
        int output = operand.pop();
        while(!operand.isEmpty() || !operator.isEmpty()) {
            switch (operator.pop()) {
                case "+" -> output = operand.pop() + output;
                case "-" -> output = operand.pop() - output;
                case "*" -> output = operand.pop() * output;
                case "/" -> output = operand.pop() / output;
            }
        }
        return output;
    }
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)) {
            String input = scn.next();
            String[] calculate = input.split("(?=[+\\-*/()])|(?<=[+\\-*/()])");
            System.out.println(countInfix(calculate));
        }
    }
}