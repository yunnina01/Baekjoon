import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long res = 0;
        for(int i = 0, now; i < N; i++, pq.offer(now)) {
            now = i - Integer.parseInt(st.nextToken());
            if(pq.isEmpty() || pq.peek() >= now)
                continue;
            pq.offer(now);
            res += now - pq.poll();
        }
        System.out.println(res);
    }
}