import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Stack<Tower> stack = new Stack<>();
        int[] res = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());

            if(stack.isEmpty())
                stack.push(new Tower(i, height));
            else if(stack.peek().h <= height) {
                while(true) {
                    if(stack.isEmpty()) {
                        stack.push(new Tower(i, height));
                        break;
                    } else if(stack.peek().h > height) {
                        res[i] = stack.peek().idx;
                        stack.push(new Tower(i, height));
                        break;
                    } else
                        stack.pop();
                }
            } else {
                res[i] = stack.peek().idx;
                stack.push(new Tower(i, height));
            }
        }

        for(int i = 1; i <= N; i++)
            sb.append(res[i] + " ");
        System.out.println(sb);
    }
}

class Tower {
    int idx, h;

    Tower(int idx, int h){
        this.idx = idx;
        this.h = h;
    }
}