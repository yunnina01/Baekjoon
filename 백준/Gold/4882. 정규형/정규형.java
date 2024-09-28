import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = 1;
        while(true) {
            String s = br.readLine();
            if(s.charAt(1) == ')')
                break;
            sb.append(TC++ + ". " + (getTreeResult(s) ? "true\n" : "false\n"));
        }
        System.out.print(sb);
    }

    static int getDeepestLevel(String s) {
        int max = 0;
        int level = 0;
        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case '(':
                    level++;
                    max = Math.max(max, level);
                    break;
                case ')':
                    level--;
            }
        }
        return max;
    }

    static boolean getTreeResult(String s) {
        Stack<Integer> stack = new Stack<>();
        int level = 0;
        int max = getDeepestLevel(s);
        for(int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    level++;
                    stack.push(2);
                    break;
                case 'T':
                    stack.push(1);
                    break;
                case 'F':
                    stack.push(0);
                    break;
                case ')':
                    int top = stack.pop();
                    while(stack.peek() != 2) {
                        if(((max - level) & 1) == 0)
                            top &= stack.pop();
                        else
                            top |= stack.pop();
                    }
                    stack.pop();
                    level--;
                    stack.push(top);
            }
        }
        return stack.pop() == 1 ? true : false;
    }
}