import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long[][] dp = new long[11][2001];
        Arrays.fill(dp[0], 1);

        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 2001; j++)
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j / 2];
        }

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(dp[n][m] + "\n");
        }
        System.out.print(sb);
    }
}