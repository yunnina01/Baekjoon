import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        long[][][] links = new long[T][N][N];
        for(int i = 0; i < T; i++) {
            int M = Integer.parseInt(br.readLine());
            for(int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                links[i][a][b] = c;
            }
        }
        
        long[][] loop = links[0];
        for(int i = 1; i < T; i++)
            loop = matrixMul(loop, links[i]);

        long[][] res;
        if(D / T != 0) {
            res = matrixPow(loop, D / T);
            for(int i = 1; i <= D % T; i++)
                res = matrixMul(res, links[i - 1]);
        } else{
            res = D==0?new long[N][N]:links[0];
            for(int i = 2; i <= D % T; i++)
                res = matrixMul(res, links[i - 1]);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                sb.append(res[i][j] + " ");
            sb.append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static long[][] matrixMul(long[][] a, long[][] b){
        long[][] c = new long[b[0].length][a.length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                for(int k = 0; k < b[0].length; k++)
                    c[i][k] = (c[i][k] + (a[i][j] * b[j][k]) % MOD) % MOD;
            }
        }
        return c;
    }

    static long[][] matrixPow(long[][] a, long num){
        if(num == 1)
            return a;
        else if(num % 2 == 1)
            return matrixMul(matrixPow(a, num - 1), a);
        else
            return matrixPow(matrixMul(a, a), num / 2);
    }
}