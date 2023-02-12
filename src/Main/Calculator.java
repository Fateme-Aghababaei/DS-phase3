package Main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import Main.Tree.Position;
import Main.Tree.Node;

public class Calculator {
    private static final ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "^"));

    public static boolean Validate(String expression) {
        Stack<String> parenthesis = new Stack<String>();
        boolean readingNumber = false, decimalPoint = false;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ' ') continue;
            if (c == '(') {
                if (expression.charAt(i + 1) == ')') return false;
                parenthesis.push("(");
                readingNumber = false;
                decimalPoint = false;
            }
            else if (c == ')') {
                String data = parenthesis.pop();
                if (data == null) return false;
                readingNumber = false;
                decimalPoint = false;
            }

            if (operators.contains(String.valueOf(c))) {
                if (i == expression.length() - 1) return false;
                String s = String.valueOf(expression.charAt(i + 1));
                if (operators.contains(s) && !s.equals("-")) return false;
                if (i == 0 && c != '+' && c != '-') return false;
                readingNumber = false;
                decimalPoint = false;
            }
            if (Character.isAlphabetic(c)) return false;
            if (Character.isDigit(c)) readingNumber = true;
            if (c == '.') {
                if (decimalPoint) return false;
                readingNumber = true;
                decimalPoint = true;
            }
        }
        return parenthesis.isEmpty();
    } //O(n)

    private static int Precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
            case '!':
                return 3;
            default:
                return 0;
        }
    }

    public static String toPostfix(String expression) {
        Stack<String> stack = new Stack<String>();
        stack.push("#");
        StringBuilder postfix = new StringBuilder();

        expression = expression.replace(" ", ""); //O(n)

        String prev = "";
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c) || c == '.') {
                postfix.append(c);
                while (i < expression.length() - 1 && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    postfix.append(expression.charAt(i + 1));
                    i++;
                }
                postfix.append(" ");
            }
            else if (c == '(')
                stack.push("(");
            else if (c == '^')
                stack.push("^");
            else if (c == ')') {
                while (!stack.peek().equals("#") && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop();
            }
            else {
                if (c == '-' && (prev.equals("(") || prev.equals("") || operators.contains(prev))) {
                    stack.push("!");
                }
                else if (Precedence(c) > Precedence(stack.peek().charAt(0)))
                    stack.push(String.valueOf(c));
                else {
                    while (!stack.peek().equals("#") && Precedence(c) <= Precedence(stack.peek().charAt(0))) {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.push(String.valueOf(c));
                }
            }
            prev = String.valueOf(c);
        }

        while (!stack.peek().equals("#"))
            postfix.append(stack.pop()).append(" ");

        return postfix.toString();
    }

    public static String toInfix(String expression) {
        Stack<String> stack = new Stack<String>();
        String[] ex = expression.split(" ");

        for (String s : ex) {
            if (isNumeric(s)) stack.push(s);
            else {
                String op1 = stack.pop();
                if (s.equals("!")) {
                    stack.push("(-" + op1 + ")");
                }
                else {
                    String op2 = stack.pop();
                    stack.push("(" + op2 + s + op1 + ")");
                }
            }
        }
        return stack.pop();
    }

    private static double Operate(double d1, double d2, String op) throws Exception{
        switch (op) {
            case "+":
                return d1 + d2;
            case "-":
                return d1 - d2;
            case "*":
                return d1 * d2;
            case "/":
                if (d2 == 0) throw new ArithmeticException("Division by Zero");
                return d1 / d2;
            case "^":
                return Math.pow(d1, d2);
            case "!":
                return -1 * d1;
            default:
                throw new Exception();
        }
    }

    public static ArrayList<String> CalculateExpressionWithSteps(String expression) throws Exception{
        ArrayList<String> steps = new ArrayList<>();

        String postfix = toPostfix(expression);
        ArrayList<String> ex = new ArrayList<>(Arrays.asList(postfix.split(" ")));
        Stack<String> stack = new Stack<String>();
        while (ex.size() != 0) {
            String s = ex.get(0);
            if (operators.contains(s)) {
                double d1 = Double.parseDouble(stack.pop());
                double d2 = Double.parseDouble(stack.pop());
                double res = Operate(d2, d1, s);

                ArrayList<String> ex_copy = new ArrayList<>(ex);
                Stack<String> stack_copy = new Stack<String>(stack);

                ex_copy.add(0, String.valueOf(d1));
                ex_copy.add(0, String.valueOf(d2));
                while (!stack_copy.isEmpty()) ex_copy.add(0, stack_copy.pop());
                steps.add(toInfix(ArrayListToString(ex_copy)));

                DecimalFormat decimalFormat = new DecimalFormat("#.####");
                stack.push(decimalFormat.format(res));
            }
            else if (s.equals("!")) {
                double d1 = Double.parseDouble(stack.pop());
                double res = Operate(d1, 1, s);


                ArrayList<String> ex_copy = new ArrayList<>(ex);
                Stack<String> stack_copy = new Stack<String>(stack);

                ex_copy.add(0, String.valueOf(d1));
                while (!stack_copy.isEmpty()) ex_copy.add(0, stack_copy.pop());
                steps.add(toInfix(ArrayListToString(ex_copy)));

                stack.push(String.valueOf(res));
            }
            else
                stack.push(s);
            ex.remove(0);
        }
        steps.add(stack.pop());
        return steps;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private static String ArrayListToString(ArrayList<String> arr) {
        StringBuilder s = new StringBuilder();
        for (String str : arr) {
            s.append(str).append(" ");
        }
        return s.toString();
    }

    public static Node InfixToTree(String expression) {
        Stack<Position> positionStack = new Stack<Position>();
        Stack<String> stringStack = new Stack<String>();
        String prev = "";

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ' ') continue;
            if (c == '(') stringStack.push("(");
            else if (Character.isDigit(c) || c == '.') {
                String s = String.valueOf(c);
                while (i < expression.length() - 1 && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    s += expression.charAt(++i);
                }
                positionStack.push(new Node(s));
            }
            else if (c == '-' && (prev.equals("(") || prev.equals("") || operators.contains(prev))) {
                c = '!';
                while (!stringStack.isEmpty() && !stringStack.peek().equals("(")
                        && Precedence(stringStack.peek().charAt(0)) >= Precedence(c)
                ) {
                    Node node = new Node(stringStack.pop());
                    Node node1 = (Node) positionStack.pop();
                    node.setLeftChild(node1);
                    positionStack.push(node);
                }
                stringStack.push(String.valueOf(c));
            }
            else if (operators.contains(String.valueOf(c))) {
                while (!stringStack.isEmpty() && !stringStack.peek().equals("(")
                        && ((c != '^' && Precedence(stringStack.peek().charAt(0)) >= Precedence(c)
                        || (c == '^'
                        && Precedence(stringStack.peek().charAt(0)) > Precedence(c))))
                ) {
                    Node node = new Node(stringStack.pop());
                    if (node.getData().equals("!")) {
                        Node node1 = (Node) positionStack.pop();
                        node.setLeftChild(node1);
                    }
                    else {
                        Node node1 = (Node) positionStack.pop();
                        Node node2 = (Node) positionStack.pop();
                        node.setLeftChild(node2);
                        node.setRightChild(node1);
                    }
                    positionStack.push(node);
                }
                stringStack.push(String.valueOf(c));
            }
            else if (c == ')') {
                while (!stringStack.isEmpty() && !stringStack.peek().equals("("))
                {
                    Node node = new Node(stringStack.pop());
                    if (node.getData().equals("!")) {
                        Node node1 = (Node) positionStack.pop();
                        node.setLeftChild(node1);
                    }
                    else {
                        Node node1 = (Node) positionStack.pop();
                        Node node2 = (Node) positionStack.pop();
                        node.setLeftChild(node2);
                        node.setRightChild(node1);
                    }
                    positionStack.push(node);
                }
                stringStack.pop();
            }
            prev = String.valueOf(c);
        }

        return (Node) positionStack.peek();
    }
}
