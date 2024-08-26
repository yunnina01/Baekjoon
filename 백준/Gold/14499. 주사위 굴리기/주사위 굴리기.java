import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static int[][] map;
    static int[] dice = new int[7];
    static int N, M, x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }
    }

    static void move(int pos) {
        int ny = y + DIRECTIONS[pos - 1][0];
        int nx = x + DIRECTIONS[pos - 1][1];
        if (ny < 0 || nx < 0 || ny > M - 1 || nx > N - 1)
            return;

        approve(pos, ny, nx);
        y = ny;
        x = nx;
    }

    static void approve(int pos, int row, int col) {
        int tmp = dice[3];
        if (pos == 1) {
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        } else if (pos == 2) {
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        } else if (pos == 3) {
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = tmp;
        } else if (pos == 4) {
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }

        if(map[col][row] == 0)
            map[col][row] = dice[6];
        else {
            dice[6] = map[col][row];
            map[col][row] = 0;
        }
        System.out.println(dice[3]);
    }
}