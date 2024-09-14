import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static final int INF = Integer.MAX_VALUE;
    static int N, M, blankCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String input;
        int caseNum = 1;

        while((input = br.readLine()) != null) {
            sb.append("Case " + caseNum++ + ": ");

            st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            char[][] map = new char[N][M];
            blankCnt = 0;
            for(int i = 0; i < N; i++) {
                input = br.readLine();
                for(int j = 0; j < M; j++) {
                    map[i][j] = input.charAt(j);
                    if(map[i][j] == '.')
                        blankCnt++;
                }
            }

            int min = INF;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == '.') {
                        map[i][j] = '-';
                        min = Math.min(min, backTracking(new Point(i, j, 1), map, 0));
                        map[i][j] = '.';
                    }
                }
            }
            if(min == INF)
                sb.append(-1 + "\n");
            else
                sb.append(min + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static int backTracking(Point cur, char[][] lastMap, int depth) {
        if(cur.cnt == blankCnt)
            return depth;

        int min = INF;
        for(int i = 0; i < 4; i++) {
            int ny = cur.y + dy[i];
            int nx = cur.x + dx[i];
            if(isInner(ny, nx) && lastMap[ny][nx] == '.') {
                char[][] tmpMap = copyMap(lastMap);
                Point nextP = fillColor(tmpMap, i, ny, nx, cur.cnt);
                min = Math.min(backTracking(nextP, tmpMap, depth + 1), min);
            }
        }
        return min;
    }

    static boolean isInner(int ny, int nx) {
        return ny >= 0 && ny < N && nx >= 0 && nx < M;
    }

    static char[][] copyMap(char[][] lastMap) {
        char[][] tmpMap = new char[N][M];
        for(int i = 0; i < N; i ++)
            System.arraycopy(lastMap[i], 0, tmpMap[i], 0, M);
        return tmpMap;
    }

    static Point fillColor(char[][] tmpMap, int dir, int y, int x, int cnt) {
        tmpMap[y][x] = '-';
        cnt++;
        while(isInner(y + dy[dir], x + dx[dir]) && tmpMap[y + dy[dir]][x + dx[dir]] == '.') {
            y += dy[dir];
            x += dx[dir];
            tmpMap[y][x] = '-';
            cnt ++;
        }
        return new Point(y, x, cnt);
    }
}

class Point{
    int y, x, cnt;

    Point(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}