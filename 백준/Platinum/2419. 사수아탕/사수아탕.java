import java.io.*;
import java.util.*;

public class Main {
    static int[] dp, x;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        x = new int[n + 1];
        for(int i = 1; i <= n; i++)
            x[i] = Integer.parseInt(br.readLine());
        Arrays.sort(x);

        dp = new int[(n + 1) * (n + 1)];
        int res = 0;
        for(int i = 1; i <= n; i++) {
            int idx = Arrays.binarySearch(x, 0);
            Arrays.fill(dp, -1);
            res = Math.max(res, i * m - recur(idx, idx, i));
        }
        System.out.println(res);
    }

    static int recur(int start, int end, int left) {
        if(left < 1)
            return 0;
        int now = start * (n + 1) + end;
        if(dp[now] != -1)
            return dp[now];

        int low = Math.min(start, end);
        int high = Math.max(start, end);
        dp[now] = Integer.MAX_VALUE;
        if(high < n)
            dp[now] = Math.min(dp[now], recur(low, high + 1, left - 1) + left * (x[high + 1] - x[end]));
        if(low > 0)
            dp[now] = Math.min(dp[now], recur(high, low - 1, left - 1) + left * (x[end] - x[low - 1]));
        return dp[now];
    }
}