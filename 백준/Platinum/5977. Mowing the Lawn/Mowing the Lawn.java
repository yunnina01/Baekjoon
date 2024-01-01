import java.io.*;
import java.util.*;

public class Main {
    static long[] totalSum, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cowArr = new int[N + 1];
        for(int i = 1; i <= N; i++)
            cowArr[i] = Integer.parseInt(br.readLine());

        totalSum = new long[N + 1];
        totalSum[1] = cowArr[1];
        for(int i = 2; i <= N; i++)
            totalSum[i] = totalSum[i - 1] + cowArr[i];

        dp = new long[N + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            while(!deque.isEmpty() && deque.peekFirst() < i - K)
                deque.pollFirst();
            while(!deque.isEmpty() && calc(deque.peekLast()) <= calc(i))
                deque.pollLast();
            deque.offerLast(i);
            dp[i] = totalSum[i] + calc(deque.peekFirst());
            if(i <= K)
                dp[i] = Math.max(dp[i], totalSum[i]);
        }
        bw.write(String.valueOf(dp[N]) + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static long calc(int i) {
        return dp[i - 1] - totalSum[i];
    }
}