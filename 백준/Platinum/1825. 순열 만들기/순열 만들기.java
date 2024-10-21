import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        if(M + K > N + 1 || N > M * K) {
            System.out.println(-1);
            return;
        }

        ArrayDeque<Long> deque = new ArrayDeque<>();
        long left = N - M;
        for(int i = 0; i < M; i++) {
            deque.offerFirst(1 + Math.min(K - 1, left));
            left = Math.max(left - K + 1, 0);
        }
        for(long i = 1; !deque.isEmpty();) {
            long j = deque.pollFirst();
            i += j;
            for(int k = 1; k <= j; k++)
                sb.append(i - k + " ");
        }
        sb.append("\n");

        left = N - K;
        for(int i = 0; i < K; i++) {
            deque.offerFirst(1 + Math.min(M - 1, left));
            left = Math.max(left - M + 1, 0);
        }
        for(long i = N; !deque.isEmpty();) {
            long j = deque.pollFirst();
            i -= j;
            for(int k = 1; k <= j; k++)
                sb.append(i + k + " ");
        }
        System.out.println(sb);
    }
}