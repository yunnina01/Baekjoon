import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_001;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());

        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write((pairing(a, w) ? "YES" : "NO") + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean pairing(int[] a, int weight) {
        int[] dp = new int[weight + 1];
        Arrays.fill(dp, INF);

        for(int i = 0; i < a.length; i++) {
            for(int j = i + 1; j < a.length; j++) {
                int sum = a[i] + a[j];
                int diff = weight - sum;
                if(diff <= 0)
                    continue;

                if(dp[diff] < i)
                    return true;
                dp[sum] = Math.min(dp[sum], j);
            }
        }
        return false;
    }
}