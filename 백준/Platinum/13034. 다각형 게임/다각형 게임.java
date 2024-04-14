import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[0] = dp[1] = 0;
        dp[2] = 1;

        for(int i = 3; i <= N; i++) {
            boolean[] check = new boolean[16];
            for(int j = 0; j <= i - 2; j++) {
                int temp = dp[j] ^ dp[i - j - 2];
                check[temp] = true;
            }

            for(int j = 0; j <= 15; j++) {
                if(!check[j]) {
                    dp[i] = j;
                    break;
                }
            }
        }
        System.out.println(dp[N] == 0 ? 2 : 1);
    }
}