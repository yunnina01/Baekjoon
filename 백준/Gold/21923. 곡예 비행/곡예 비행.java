import java.io.*;
import java.util.*;

public class Main {
    private static final int MIN = Integer.MIN_VALUE >> 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine(), " ", false);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][M + 1];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            for(int j = 1; j <= M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] up = new int[N + 1][M + 1];
        int[][] down = new int[N + 1][M + 2];
        if(M > 1) {
            up[N][M] = MIN;
            down[N][1] = MIN;
        }
        for(int i = 2; i < M; i++) {
            up[N][i] = MIN;
            down[N][i] = MIN;
        }

        for(int i = N - 1; i >= 0; i--) {
            up[i][0] = MIN;
            for(int j = 1; j <= M; j++)
                up[i][j] = Math.max(up[i + 1][j], up[i][j - 1]) + map[i][j];
        }

        int res = MIN;
        for(int i = N - 1; i >= 0; i--) {
            down[i][M + 1] = MIN;
            for(int j = M; j > 0; j--) {
                down[i][j] = Math.max(down[i + 1][j], down[i][j + 1]) + map[i][j];
                res = Math.max(res, up[i][j] + down[i][j]);
            }
        }
        System.out.print(res);
    }
}