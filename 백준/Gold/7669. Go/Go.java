import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static final int BLACK = 1, WHITE = 2;
    static int[][] map;
    static boolean[][] visit;
    static int N, B, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            String line = br.readLine();
            if(line == null || line.equals(""))
                continue;

            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            if(N == 0)
                break;
            B = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < B; i++)
                map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = BLACK;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < W; i++)
                map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = WHITE;

            visit = new boolean[N][N];
            int black = 0;
            int white = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 0)
                        continue;

                    for(int[] dir : DIRECTIONS) {
                        int ny = i + dir[0];
                        int nx = j + dir[1];
                        if(isOutBound(ny, nx) || visit[ny][nx] || map[ny][nx] != 0)
                            continue;

                        int res = BFS(ny, nx, map[i][j]);
                        if(res == -1)
                            continue;

                        if(map[i][j] == BLACK)
                            black += res;
                        else
                            white += res;
                    }
                }
            }
            if(black == white)
                sb.append("Draw");
            else if(black > white)
                sb.append("Black wins by " + (black - white));
            else
                sb.append("White wins by " + (white - black));
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean isOutBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }

    static int BFS(int y, int x, int type) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(y, x));
        visit[y][x] = true;
        int cnt = 1;
        boolean flag = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for(int[] dir: DIRECTIONS) {
                int ny = now.y + dir[0];
                int nx = now.x + dir[1];
                if(isOutBound(ny, nx) || visit[ny][nx])
                    continue;
                if(map[ny][nx] != 0 && map[ny][nx] != type)
                    flag = false;

                if(map[ny][nx] == 0) {
                    queue.offer(new Point(ny, nx));
                    visit[ny][nx] = true;
                    cnt++;
                }
            }
        }
        return flag ? cnt : -1;
    }
}

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}