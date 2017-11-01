import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class infixToPostfix {
    public static String convert(String s) {
        String[] strings = s.split("\\s+");

        StringBuilder sb = new StringBuilder();
        Stack stack = new Stack();
        for (int i = 0; i < strings.length; ++i) {
            if (isRealNumber(strings[i])) {
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

    public static boolean isOpr(String s) {
        return (s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/") || s.equals("POW"))?true:false;
    }


    private static boolean isMatch(String regex, String orginal){
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

    public static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

    public static boolean isNegativeInteger(String orginal) {
        return isMatch("^-[1-9]\\d*", orginal);
    }

    public static boolean isWholeNumber(String orginal) {
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
    }

    public static boolean isPositiveDecimal(String orginal){
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);
    }

    public static boolean isNegativeDecimal(String orginal){
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);
    }

    public static boolean isDecimal(String orginal){
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
    }

    public static boolean isRealNumber(String orginal){
        return isWholeNumber(orginal) || isDecimal(orginal);
    }
}
