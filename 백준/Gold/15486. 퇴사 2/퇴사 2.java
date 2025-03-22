import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 2];
        int[] P = new int[N + 2];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2];
        int max = 0;
        for(int i = 1; i <= N + 1; i++) {
            if(dp[i] > max)
                max = dp[i];
            int next = i + T[i];
            if(next < N + 2)
                dp[next] = Math.max(dp[next], max + P[i]);
        }
        System.out.println(dp[N + 1]);
    }
}