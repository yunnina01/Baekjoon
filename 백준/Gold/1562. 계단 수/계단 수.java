import java.io.*;

public class Main {
    static final int MOD = 1_000_000_000;
    static int[][][] cache;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        cache = new int[1 << 11][101][10];
        long res = 0;
        for(int i = 1; i < 10; i++) {
            res += DP(1, i, 1 << i);
            res %= MOD;
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static int DP(int idx, int num, int bit) {
        if(cache[bit][idx][num] != 0)
            return cache[bit][idx][num];
        if(idx == N)
            return bit == (1 << 10) - 1 ? 1 : 0;
        
        int res = 0;
        if(num + 1 < 10)
            res += DP(idx + 1, num + 1, bit | 1 << (num + 1));
        if(num - 1 >= 0)
            res += DP(idx + 1, num - 1, bit | 1 << (num - 1));
        return cache[bit][idx][num] = res % MOD;
    }
}