import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cheeseNum = 0, time = 0;
    static int[][] map;
    static int[][] air;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                    cheeseNum++;
            }
        }

        while(cheeseNum != 0)
            cheese();
        System.out.println(time);
    }

    static void cheese() {
        Queue<Point> q = new LinkedList<>();
        air = new int[N][M];
        q.offer(new Point(0, 0));
        air[0][0] = -1;

        while(!q.isEmpty()) {
            Point p = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i], ny = p.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if(map[nx][ny] == 1)
                    air[nx][ny]++;
                if(map[nx][ny] == 0 && air[nx][ny] == 0) {
                    air[nx][ny] = -1;
                    q.offer(new Point(nx, ny));
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(air[i][j] >= 2) {
                    cheeseNum--;
                    map[i][j] = 0;
                }
            }
        }
        time++;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}