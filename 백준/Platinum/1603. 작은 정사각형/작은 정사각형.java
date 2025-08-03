import java.io.*;
import java.util.*;

public class Main {
    static long[] dp = new long[1 << 20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int TC = 0; TC < 3; TC++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int G = 0;
            for(int i = 0; i < N - N % 2; i += 2) {
                char[] up = br.readLine().toCharArray();
                char[] down = br.readLine().toCharArray();

                int status = 0;
                for(int j = 0; j < M; j++) {
                    status <<= 2;
                    status |= (up[j] == '.' ? 2 : 0) | (down[j] == '.' ? 1 : 0);
                }

                Arrays.fill(dp, -1);
                dp[0] = 0;
                grundy(status);
                G ^= dp[status];
            }

            if(N % 2 == 1) {
                char[] up = br.readLine().toCharArray();

                int status = 0;
                for(int i = 0; i < M; i++) {
                    status <<= 2;
                    status |= (up[i] == '.' ? 2 : 0);
                }

                Arrays.fill(dp, -1);
                dp[0] = 0;
                grundy(status);
                G ^= dp[status];
            }
            sb.append((G == 0 ? "M" : "Y") + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void grundy(int status) {
        if(dp[status] != -1 || status == 0)
            return;

        HashSet<Long> mex = new HashSet<>();
        for(int i = 15; i < (1 << 20) && i <= status; i <<= 2) {
            if((status & i) != i)
                continue;
            grundy(status ^ i);
            mex.add(dp[status ^ i]);
        }

        for(int i = 1; i < (1 << 20); i <<= 1) {
            if((status & i) == 0)
                continue;
            grundy(status ^ i);
            mex.add(dp[status ^ i]);
        }

        dp[status] = 0;
        while(mex.contains(dp[status]))
            dp[status]++;
    }
}