import java.io.*;
import java.util.*;

public class Main {
    static long[][] sum, cost, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] arr = new long[N + 1][2];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, 1, N + 1, Comparator.comparingLong(o -> o[0]));

        dp = new long[2][N + 1];
        sum = new long[N + 1][N + 1];
        cost = new long[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            sum[i][0] = sum[i - 1][0] + arr[i][0];
            for(int j = i - 1; j > 0; j--)
                sum[i][j] = sum[i][j + 1] + arr[j][1] * (arr[i][0] - arr[j][0]);
            for(int j = i + 1; j <= N; j++)
                sum[i][j] = sum[i][j - 1] + arr[j][1] * (arr[j][0] - arr[i][0]);
        }

        for(int i = 1; i <= N; i++) {
            for(int j = i + 1, k = i; j <= N; j++) {
                while(k < j && sum[k][i] + sum[k][j] > sum[k + 1][i] + sum[k + 1][j])
                    k++;
                cost[i][j] = sum[k][i]+sum[k][j];
            }
        }

        for(int i = 1; i <= N; i++)
            dp[1][i] = cost[1][i];
        for(int i = 2; i <= K; i++)
            recur(i, i, N, i - 1, N - 1);
        System.out.println(dp[K % 2][N]);
    }

    static void recur(int idx, int start, int end, int left, int right){
        if(start > end)
            return;
        int mid = (start + end) >> 1;

        dp[idx % 2][mid] = Long.MAX_VALUE;
        int center = 0;
        for(int i = left; i <= right; i++) {
            long tmp = dp[(idx + 1) % 2][i] + cost[i + 1][mid];
            if(dp[idx % 2][mid] <= tmp)
                continue;
            dp[idx % 2][mid] = tmp;
            center = i;
        }
        recur(idx, start, mid - 1, left, center);
        recur(idx, mid + 1, end, center, right);
    }
}