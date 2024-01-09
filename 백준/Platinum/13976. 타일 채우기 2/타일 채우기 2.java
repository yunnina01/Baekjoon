import java.io.*;

public class Main {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Long.parseLong(br.readLine());
        if(N % 2 > 0)
            bw.write("0");
        else {
            long[][] base = {{0, 1}, {-1, 4}};
            long[][] res = {{1, 0}, {3, 0}};

            base = matrixPowerMod(base, N / 2);
            res = matrixMultiplyMod(base, res);

            bw.write(String.valueOf(res[0][0]));
        }
        bw.newLine();
        br.close();
        bw.flush();
        bw.close();
    }

    static long[][] matrixMultiplyMod(long[][] A, long[][] B) {
        long[][] ret = new long[2][2];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 2; k++) {
                    long tmp = (A[i][k] * B[k][j] + MOD) % MOD;
                    ret[i][j] = (ret[i][j] + tmp) % MOD;
                }
            }
        }
        return ret;
    }

    static long[][] matrixPowerMod(long[][] base, long pow) {
        long[][] ret = {{1, 0}, {0, 1}};
        while(pow > 0) {
            if(pow % 2 > 0)
                ret = matrixMultiplyMod(ret, base);
            base = matrixMultiplyMod(base, base);
            pow /= 2;
        }
        return ret;
    }
}