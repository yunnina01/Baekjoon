import java.io.*;
import java.util.*;

public class Main {
    static long[][][][] dp;
    static boolean[][][][] visit;
    static char[][] map, rot;
    static int RC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new char[R][];
        for(int i = 0; i < R; i++)
            map[i] = br.readLine().toCharArray();

        RC = (R + C) >> 1;
        rot = new char[RC][RC];
        for(int i = 0; i < R; i++) {
            for(int j = i % 2; j < C; j += 2)
                rot[(i + j) >> 1][(R - 1 + j - i) >> 1] = map[i][j];
        }

        dp = new long[RC][RC][RC][RC];
        visit = new boolean[RC][RC][RC][RC];
        int G = 0;
        G ^= grundy(0, 0, RC - 1, RC - 1);

        rot = new char[RC][RC];
        for(int i = 0; i < R; i++) {
            for(int j = 1 - i % 2; j < C; j += 2)
                rot[(i + j) >> 1][(R - 1 + j - i) >> 1] = map[i][j];
        }

        dp = new long[RC][RC][RC][RC];
        visit = new boolean[RC][RC][RC][RC];
        G ^= grundy(0, 0, RC - 1, RC - 1);
        System.out.println(G == 0 ? "cubelover" : "koosaga");
    }

    static long grundy(int r1, int c1, int r2, int c2) {
        if(!isIn(r1, c1) || !isIn(r2, c2) || r1 > r2 || c1 > c2)
            return 0;
        if(visit[r1][c1][r2][c2])
            return dp[r1][c1][r2][c2];
        HashSet<Long> mex = new HashSet<>();
        for(int i = r1; i <= r2; i++) {
            for(int j = c1; j <= c2; j++) {
                switch(rot[i][j]) {
                    case '\u0000':
                        continue;
                    case 'X':
                        mex.add(grundy(r1, c1, i - 1, j - 1) ^ grundy(r1, j + 1, i - 1, c2) ^ grundy(i + 1, c1, r2, j - 1) ^ grundy(i + 1, j + 1, r2, c2));
                        break;
                    case 'L':
                        mex.add(grundy(r1, c1, i - 1, c2) ^ grundy(i + 1, c1, r2, c2));
                        break;
                    case 'R':
                        mex.add(grundy(r1, c1, r2, j - 1) ^ grundy(r1, j + 1, r2, c2));
                        break;
                }
            }
        }

        while(mex.contains(dp[r1][c1][r2][c2]))
            dp[r1][c1][r2][c2]++;
        visit[r1][c1][r2][c2] = true;
        return dp[r1][c1][r2][c2];
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < RC && 0 <= c && c < RC;
    }
}