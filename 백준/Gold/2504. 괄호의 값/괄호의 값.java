import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int mul = 1;
        int res = 0;

        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') {
                stack.push('(');
                mul *= 2;
            } else if(str.charAt(i) == '[') {
                stack.push('[');
                mul *= 3;
            } else if(str.charAt(i) == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    res = 0;
                    break;
                } else if(str.charAt(i - 1) == '(')
                    res += mul;
                stack.pop();
                mul /= 2;
            } else {
                if(stack.isEmpty() || stack.peek() != '[') {
                    res = 0;
                    break;
                } else if(str.charAt(i - 1) == '[')
                    res += mul;
                stack.pop();
                mul /= 3;
            }
        }
        bw.write((stack.isEmpty() ? res : 0) + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}