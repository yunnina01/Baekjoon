import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map = new int[501][501];
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++)
                    map[j][k] = 1;
            }
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++)
                    map[j][k] = -1;
            }
        }

        res = -1;
        BFS();
        System.out.println(res);
    }

    static void BFS() {
        PriorityQueue<Game> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[501][501];
        pq.offer(new Game(0, 0, 0));
        visit[0][0] = true;

        while (!pq.isEmpty()) {
            Game now = pq.poll();
            int x = now.x;
            int y = now.y;
            int life = now.life;
            if (x == 500 && y == 500) {
                res = life;
                return;
            }
            for (int[] dir : DIRECTIONS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx > 500 || ny < 0 || ny > 500 || visit[nx][ny] || map[nx][ny] == -1)
                    continue;

                map[nx][ny] += life;
                visit[nx][ny] = true;
                pq.offer(new Game(nx, ny, map[nx][ny]));
            }
        }
    }
}

class Game implements Comparable<Game> {
    int x, y, life;

    Game(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.life = life;
    }

    @Override
    public int compareTo(Game o) {
        return this.life - o.life;
    }
}