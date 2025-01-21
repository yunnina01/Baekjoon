import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] map, visit;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new int[N][M];
        Point p1 = null, p2 = null;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(visit[i], -1);
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                    p1 = new Point(i, j, 1);
                else if(map[i][j] == 2)
                    p2 = new Point(i, j, 2);
            }
        }

        BFS(p1, p2);

        int[] res = new int[3];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int now = map[i][j];
                if(now >= 1)
                    res[now - 1]++;
            }
        }
        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }

    static void BFS(Point p1, Point p2) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(p1);
        queue.offer(p2);
        visit[p1.y][p1.x] = 0;
        visit[p2.y][p2.x] = 0;
        int cnt = 1;

        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; i++) {
                Point now = queue.poll();
                if(map[now.y][now.x] == 3)
                    continue;
                for(int[] dir : DIRECTIONS) {
                    int ny = now.y + dir[0];
                    int nx = now.x + dir[1];
                    if(!isOuter(ny, nx) && map[ny][nx] != -1){
                        if(visit[ny][nx] == cnt && map[ny][nx] == 3 - now.virus) {
                            map[ny][nx] = 3;
                            continue;
                        } else if(visit[ny][nx] == -1) {
                            queue.offer(new Point(ny, nx, now.virus));
                            map[ny][nx] = now.virus;
                            visit[ny][nx] = cnt;
                        }
                    }
                }
            }
            cnt++;
        }
    }

    static boolean isOuter(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}

class Point {
    int y, x, virus;

    Point(int y, int x, int virus){
        this.y = y;
        this.x = x;
        this.virus = virus;
    }
}