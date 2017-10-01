package XudongYu;
import java.util.Stack;

public class infixToPostfix {
    public static String convert(String s) {
        String[] strings = s.split("\\s+");

        StringBuilder sb = new StringBuilder();
        Stack stack = new Stack();
        for (int i = 0; i < strings.length; ++i) {
            if (isWord(strings[i])) {
                sb.append(strings[i] + " ");
            } else if (strings[i].equals("(")) {
                stack.push("(");
            } else if (isOpr(strings[i])) {
                if (strings[i].equals("+") || strings[i].equals("-")) {
                    if (stack.isEmpty()) {
                        stack.push(strings[i]);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("POW")) {
                            sb.append(stack.pop() + " ");
                            if (stack.isEmpty()) {
                                break;
                            }
                        }
                        stack.push(strings[i]);
                    }
                } else if (strings[i].equals("*") || strings[i].equals("/")) {
                    if (stack.isEmpty()) {
                        stack.push(strings[i]);
                    } else {
                        while (stack.peek().equals("POW")) {
                            sb.append(stack.pop() + " ");
                            if (stack.isEmpty()) {
                                break;
                            }
                        }
                        stack.push(strings[i]);
                    }
                }
                else if (strings[i].equals("POW")) {
                    stack.push(strings[i]);
                }
            } else if (strings[i].equals(")")) {
                while (!stack.peek().equals("(")) {
                    sb.append(stack.pop() + " ");
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        String res = "";
        return sb.toString();
    }
    public static boolean isWord(String s) {
            for (int i = 0; i < s.length(); ++i) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }
        return true;
    }
    public static boolean isOpr(String s) {
        return (s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/") || s.equals("POW"))?true:false;
    }
}
