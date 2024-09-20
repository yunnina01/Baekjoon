import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        br.readLine();

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            pq.offer(Integer.parseInt(st.nextToken()));

        long res = 0;
        while(!pq.isEmpty()) {
            res += pq.poll();
            sb.append(res + "\n");
        }
        System.out.print(sb.toString());
    }
}