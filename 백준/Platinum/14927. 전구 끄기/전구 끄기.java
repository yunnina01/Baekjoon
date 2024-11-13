import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}};
    static boolean[][] map, raw;
    static int N, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        raw = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                raw[i][j] = Integer.parseInt(st.nextToken()) == 1;
        }

        map = new boolean[N][N];
        int res = 400;
        for(int i = (1 << N) - 1; i >= 0; i--) {
            cnt = 0;
            for(int j = 0; j < N; j++)
                map[j] = Arrays.copyOf(raw[j], N);

            for(int j = 0; j < N; j++) {
                if((i & (1 << j)) != 0)
                    flip(0, j);
            }
            for(int r = 1; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(map[r - 1][c])
                        flip(r, c);
                }
            }

            boolean valid = true;
            for(int c = 0; c < N; c++)
                valid &= !map[N - 1][c];
            if(valid)
                res = Math.min(res, cnt);
        }
        System.out.println(res == 400 ? -1 : res);
    }

    static void flip(int r, int c) {
        cnt++;
        for(int i = 0; i < 5; i++) {
            int nr = r + DIRECTIONS[i][0];
            int nc = c + DIRECTIONS[i][1];
            if(!isIn(nr,nc))
                continue;
            map[nr][nc] = !map[nr][nc];
        }
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}