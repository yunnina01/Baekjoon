import java.io.*;
import java.util.*;

public class Main {
    static long[][] dp;
    static long[] sum;
    static int[][] C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        dp = new long[G + 1][L + 1];
        sum = new long[L + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= L; i++)
            dp[1][i] = (sum[i] += sum[i - 1] + Long.parseLong(st.nextToken())) * i;

        C = new int[G + 1][L + 1];
        for(int i = 2; i <= G; i++)
            recur(i, 0, L, 0, L);
        System.out.println(dp[G][L]);
    }

    static void recur(int idx, int left, int right, int cl, int cr) {
        if(left > right)
            return;
        int mid = (left + right) >> 1;
        dp[idx][mid] = -1;
        C[idx][mid] = -1;
        for(int i = cl; i <= cr; i++) {
            long tmp = dp[idx - 1][i] + (sum[mid] - sum[i]) * (mid - i);
            if(dp[idx][mid] != -1 && dp[idx][mid] <= tmp)
                continue;
            dp[idx][mid] = tmp;
            C[idx][mid] = i;
        }
        recur(idx, left, mid - 1, cl, C[idx][mid]);
        recur(idx, mid + 1, right, C[idx][mid], cr);
    }
}