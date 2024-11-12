import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sum = 0;
        int res = 0;
        for(int i = 0; i < N; i++) {
            int K = Integer.parseInt(st.nextToken());
            pq.offer(-K);
            sum += K;
            while(sum > T - i * P) {
                if(pq.isEmpty())
                    break;
                sum += pq.poll();
            }
            res = Math.max(res, pq.size());
        }
        System.out.println(res);
    }
}