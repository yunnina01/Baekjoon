import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[][] factor = {{1, 1}, {1, 0}};
    static HashMap<Long, long[][]> memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        memo = new HashMap<>();
        memo.put(0L, factor);
        memo.put(1L, factor);

        long[][] res = getPow((n / 2) * 2 + 1 - 1);
        System.out.println(res[0][0] - 1);
    }

    static long[][] getPow(long n) {
        if(!memo.containsKey(n)) {
            if(n % 2 == 0)
                memo.put(n, matrixMultiple(getPow(n / 2), getPow(n / 2)));
            else
                memo.put(n, matrixMultiple(factor, matrixMultiple(getPow(n / 2), getPow(n / 2))));
        }
        return memo.get(n);
    }

    static long[][] matrixMultiple(long[][] a, long[][] b) {
        long[][] ret = new long[a.length][b[0].length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < b[0].length; j++) {
                for(int k = 0; k < a[i].length; k++)
                    ret[i][j] += a[i][k] * b[k][j];
                ret[i][j] %= MOD;
            }
        }
        return ret;
    }
}