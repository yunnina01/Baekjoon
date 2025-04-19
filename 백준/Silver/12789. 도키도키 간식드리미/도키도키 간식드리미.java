import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] line = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            line[i] = Integer.parseInt(st.nextToken());
        
        Stack<Integer> stack = new Stack<>();
        int order = 1;
        for(int i = 0; i < N; i++) {
            if(line[i] != order) {
                if(!stack.isEmpty() && stack.peek() == order) {
                    stack.pop();
                    order++;
                    i--;
                } else
                    stack.push(line[i]);
            } else
                order++;
        }

        boolean flag = true;
        for(int i = 0; i < stack.size(); i++) {
            if(stack.pop() == order)
                order++;
            else {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "Nice" : "Sad");
    }
}