import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[][] map;
    static int N, M, sx, sy, ex, ey;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                if(line.charAt(j) == 'g')
                    map[i][j] = 1;
                else if(line.charAt(j) == 'F') {
                    ex = i;
                    ey = j;
                } else if(line.charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        Trash trash = BFS();
        System.out.println(trash.c + " " + trash.sc);
    }

    static Trash BFS() {
        PriorityQueue<Trash> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[N][M];
        pq.offer(new Trash(sx, sy));
        visit[sx][sy] = true;

        while(!pq.isEmpty()) {
            Trash now = pq.poll();
            if(now.x == ex && now.y == ey)
                return now;

            for(int[] dir : DIRECTIONS) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny])
                    continue;

                if(map[nx][ny] == 1)
                    pq.offer(new Trash(nx, ny, now.c + 1, now.sc));
                else {
                    boolean flag = false;
                    for(int[] ndir : DIRECTIONS) {
                        if(nx == ex && ny == ey)
                            break;

                        int nnx = nx + ndir[0];
                        int nny = ny + ndir[1];
                        if(nnx < 0 || nny < 0 || nnx >= N || nny >= M)
                            continue;

                        if(map[nnx][nny] == 1) {
                            pq.offer(new Trash(nx, ny, now.c, now.sc + 1));
                            flag = true;
                            break;
                        }
                    }
                    if(!flag)
                        pq.offer(new Trash(nx, ny, now.c, now.sc));
                }
                visit[nx][ny] = true;
            }
        }
        return new Trash(-1, -1);
    }
}

class Trash implements Comparable<Trash> {
    int x, y, c, sc;

    Trash(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Trash(int x, int y, int c, int sc) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.sc = sc;
    }

    @Override
    public int compareTo(Trash t) {
        if(this.c == t.c)
            return this.sc - t.sc;
        return this.c - t.c;
    }
}