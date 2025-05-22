import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Stack<Building> stack = new Stack<>();
        long res = 0;
        for(int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());

            if(stack.isEmpty() || stack.peek().h > h)
                stack.push(new Building(h));
            else {
                while(!stack.isEmpty() && stack.peek().h <= h) {
                    Building now = stack.pop();
                    res += now.cnt;
                    if(!stack.isEmpty())
                        stack.peek().cnt += now.cnt + 1;
                }
                stack.push(new Building(h));
            }
        }

        while(!stack.isEmpty()) {
            Building now = stack.pop();
            res += now.cnt;
            if(!stack.isEmpty())
                stack.peek().cnt += now.cnt + 1;
        }
        System.out.println(res);
    }
}

class Building {
    int h, cnt = 0;

    Building(int h) {
        this.h = h;
    }
}