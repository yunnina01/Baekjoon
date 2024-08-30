import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static int[][] map;
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;

            map = new int[h][w];
            for(int i = 0; i < 2 * h - 1; i++) {
                st = new StringTokenizer(br.readLine());
                if(i % 2 == 0) {
                    int row = i / 2;
                    for(int j = 0; j < w - 1; j++) {
                        int wall = Integer.parseInt(st.nextToken());
                        if(wall == 0)
                            continue;
                        map[row][j] += 2;
                        if(j + 1 < w)
                            map[row][j + 1] += 8;
                    }
                } else {
                    int row = i / 2 + 1;
                    for(int j = 0; j < w; j++) {
                        int wall = Integer.parseInt(st.nextToken());
                        if(wall == 0)
                            continue;
                        map[row][j] += 1;
                        map[row - 1][j] += 4;
                    }
                }
            }
            System.out.println(BFS());
        }
    }

    static int BFS() {
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visit = new boolean[h][w];
        queue.offer(new Point(0, 0, 0));
        visit[0][0] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if(now.y == h - 1 && now.x == w - 1)
                return now.n + 1;

            for(int dir = 0; dir < 4; dir++) {
                int ny = now.y + dy[dir];
                int nx = now.x + dx[dir];
                if(ny < 0 || nx < 0 || ny >= h || nx >= w || visit[ny][nx])
                    continue;
                if((map[now.y][now.x] & (1 << dir)) == (1 << dir))
                    continue;

                queue.offer(new Point(ny, nx, now.n + 1));
                visit[ny][nx] = true;
            }
        }
        return 0;
    }
}

class Point {
    int y, x, n;

    Point(int y, int x, int n) {
        this.y = y;
        this.x = x;
        this.n = n;
    }
}