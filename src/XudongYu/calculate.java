package XudongYu;
import java.util.Stack;

public class calculate {
    public static double calculate (String p) {
        String[] postfix = p.split("\\s+");
        Double a,b;
        Stack<Double> S = new Stack<>();
        for (String s : postfix) {
            if(s.equals("+")) {
                S.add(S.pop()+S.pop());
            }
            else if(s.equals("/")) {
                b = S.pop();
                a = S.pop();
                S.add(a / b);
            }
            else if(s.equals("*")) {
                S.add(S.pop() * S.pop());
            }
            else if(s.equals("-")) {
                b = S.pop();
                a = S.pop();
                S.add(a - b);
            } else if (s.equals("POW")) {
                b = S.pop();
                a = S.pop();
                Double sum = Double.valueOf(1);
                for (int i = 0; i < b; ++i) {
                    sum *= a;
                }
                S.add(sum);
            } else
             {
                S.add(Double.parseDouble(s));
            }
        }
        return S.pop();
    }
}
