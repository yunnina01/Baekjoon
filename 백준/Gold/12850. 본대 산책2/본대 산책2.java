import java.io.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[][] campus = {
        {0, 1, 1, 0, 0, 0, 0, 0},
        {1, 0, 1, 1, 0, 0, 0, 0},
        {1, 1, 0, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 1, 0, 0},
        {0, 0, 1, 1, 0, 1, 1, 0},
        {0, 0, 0, 1, 1, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 0, 1},
        {0, 0, 0, 0, 0, 1, 1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int D = Integer.parseInt(br.readLine());

        long[][] res = matrixPower(D);
        bw.write(res[0][0] + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
    
    static long[][] matrixPower(int n) {
        if(n == 1)
            return campus;

        long[][] ret = matrixPower(n / 2);
        if(n % 2 == 0)
            return matrixMultiple(ret, ret);
        else
            return matrixMultiple(matrixMultiple(ret, ret), campus);
    }
    
    static long[][] matrixMultiple(long[][] a, long[][] b) {
        long[][] ret = new long[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                for(int k = 0; k < 8; k++)
                    ret[i][j] = (ret[i][j] + a[i][k] * b[k][j]) % MOD;
            }
        }
        return ret;
    }
}