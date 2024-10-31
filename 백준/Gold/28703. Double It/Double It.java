import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        if(N == 1) {
            System.out.println(0);
            return;
        }
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        long max = Long.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            long A = Long.parseLong(st.nextToken());
            pq.offer(A);
            max = Math.max(max, A);
        }

        long min = Long.MAX_VALUE;
        long curMax = max;
        while(pq.peek() != max) {
            long now = pq.poll();
            min = Math.min(curMax - now, min);
            now *= 2;
            curMax = Math.max(now, curMax);
            pq.offer(now);
        }
        min = Math.min(min, curMax - pq.peek());
        System.out.println(min);
    }
}