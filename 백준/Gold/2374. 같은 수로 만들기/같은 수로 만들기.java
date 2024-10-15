import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        long res = 0;
        for(int i = 0; i < n; i++) {
            int A = Integer.parseInt(br.readLine());
            if(stack.isEmpty())
                stack.push(A);
            else if(stack.peek() == A)
                continue;
            else if(stack.peek() > A)
                stack.push(A);
            else {
                long diff = 0;
                while(!stack.isEmpty() && stack.peek() <= A)
                    diff = Math.max(A - stack.pop(), diff);
                res += diff;
                stack.push(A);
            }
        }

        int top = stack.pop();
        int max = top;
        while(!stack.isEmpty())
            max = stack.pop();
        res += max - top;
        System.out.println(res);
    }
}