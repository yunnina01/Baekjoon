import java.io.*;
import java.util.*;

public class Main {
    static long[][][][] dp;
    static boolean[][][][] visit;
    static char[][] map;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];
        for(int r = 0; r < R; r++)
            map[r] = br.readLine().toCharArray();

        dp = new long[R][C][R][C];
        visit = new boolean[R][C][R][C];
        System.out.println(grundy(0, 0, R - 1, C - 1) == 0 ? "Second" : "First");
    }

    static long grundy(int r1, int c1, int r2, int c2) {
        if(!isIn(r1, c1) || !isIn(r2, c2) || r1 > r2 || c1 > c2)
            return 0;
        if(visit[r1][c1][r2][c2])
            return dp[r1][c1][r2][c2];

        HashSet<Long> mex = new HashSet<>();
        for(int r = r1; r <= r2; r++) {
            for(int c = c1; c <= c2; c++) {
                if(map[r][c] == 'X')
                    continue;
                mex.add(grundy(r1, c1, r - 1, c - 1) ^ grundy(r1, c + 1, r - 1, c2) ^ grundy(r + 1, c1, r2, c - 1) ^ grundy(r + 1, c + 1, r2, c2));
            }
        }
        while(mex.contains(dp[r1][c1][r2][c2]))
            dp[r1][c1][r2][c2]++;
        visit[r1][c1][r2][c2] = true;
        return dp[r1][c1][r2][c2];
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}