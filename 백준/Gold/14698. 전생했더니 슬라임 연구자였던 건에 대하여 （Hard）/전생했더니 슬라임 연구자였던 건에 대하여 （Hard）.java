import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                long energy = Long.parseLong(st.nextToken());
                pq.offer(energy);
            }

            long res = 1;
            while(pq.size() > 1) {
                long e1 = pq.poll();
                long e2 = pq.poll();
                long energy = e1 * e2;

                res *= energy % MOD;
                res %= MOD;
                pq.offer(energy);
            }
            sb.append(res + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}