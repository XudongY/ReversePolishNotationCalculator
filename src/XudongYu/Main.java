package XudongYu;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException{
	// write your code here
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please input like these: 1 + 1 % 2 ");
            System.out.println("Every operands and number should be separated with one space");
            System.out.println("Please input your infix expression");
            String infix = scanner.nextLine();
            String postfix = infixToPostfix.convert(infix);
            System.out.println("postfix is " + postfix);
            System.out.println("reslut is " + calculate.calculate(postfix));
            System.out.println("If you want another calculate?");
            System.out.println(("yes for 1, No for q"));
            String s = scanner.next();
            if (s.equals("q")) {
                break;
            }
        }
    }
}
