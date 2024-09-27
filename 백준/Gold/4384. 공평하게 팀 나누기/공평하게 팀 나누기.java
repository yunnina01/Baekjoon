import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] weights = new int[N];
        int sum = 0;
        for(int i = 0; i < weights.length; i++)
            sum += (weights[i] = Integer.parseInt(br.readLine()));
        
        boolean[][] dp = new boolean[N + 1][sum + 1];
        dp[0][0] = true;
        for(int weight : weights) {
            for(int i = dp.length - 1; i >= 0; i--) {
                for(int j = dp[i].length - 1; j >= 0; j--) {
                    if(dp[i][j])
                        dp[i + 1][j + weight] = true;
                }
            }
        }

        int res = 0;
        for(int i = sum / 2; i >= 0; i--) {
            if(dp[N / 2][i]) {
                res = i;
                break;
            }
        }
        if(N % 2 == 1) {
            for(int i = sum / 2; i >= 0; i--) {
                if(dp[N / 2 + 1][i] && Math.abs(2 * i - sum) < Math.abs(2 * res - sum)) {
                    res = i;
                    break;
                }
            }
        }
        System.out.println(res + " " + (sum - res));
    }
}