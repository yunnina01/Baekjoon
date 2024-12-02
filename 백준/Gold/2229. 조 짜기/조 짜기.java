import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            int max = 0;
            int min = 10001;
            for(int j = i; j > 0; j--) {
                max = Math.max(max, arr[j - 1]);
                min = Math.min(min, arr[j - 1]);
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }
        System.out.println(dp[N]);
    }
}