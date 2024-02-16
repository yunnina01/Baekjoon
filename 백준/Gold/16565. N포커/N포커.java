import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int MOD = 10007;
        final int MAX = 53;
    	
        int[][] comb = new int[MAX][MAX];
        for(int i = 0; i < MAX; i++)
            comb[i][0] = 1;
        for(int i = 1; i < MAX; i++) {
            for(int j = 1; j < MAX; j++)
                comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
        }

        int N = Integer.parseInt(br.readLine());
        int res = 0;
        for(int i = 1; i <= 13 && N - i * 4 >= 0; i++) {
            if(i % 2 == 1)
                res = (res + comb[52 - i * 4][N - i * 4] * comb[13][i]) % MOD;
            else
                res = (res - (comb[52 - i * 4][N - i * 4] * comb[13][i]) % MOD + MOD) % MOD;
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}