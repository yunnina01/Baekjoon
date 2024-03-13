import java.io.*;
import java.util.*;

public class Main {
    static final int[] fiv = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765,
                            10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[] dp = new int[3000001];
        for(int i = 1; i <= 3; i++)
            dp[i] = i;

        boolean[] mex = new boolean[17];
        for(int i = 4; i <= 3000000; i++) {
            Arrays.fill(mex,false);
            for(int j : fiv) {
                if(j > i)
                    break;
                mex[dp[i - j]] = true;
            }

            for(int j = 0; j < 17; j++) {
                if(!mex[j]) {
                    dp[i] = j;
                    break;
                }
            }
        }

        Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int res = 0;
        while(st.hasMoreTokens())
            res ^= dp[Integer.parseInt(st.nextToken())];
        bw.write(res == 0 ? "cubelover" : "koosaga");
        br.close();
        bw.flush();
        bw.close();
    }
}