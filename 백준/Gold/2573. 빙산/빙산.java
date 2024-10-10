import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] map;
    static boolean[][] visit;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        int res = 0;
        while((cnt = separate()) < 2) {
            if(cnt == 0) {
                res = 0;
                break;
            }
            melt();
            res++;
        }
        System.out.println(res);
    }

    static int separate() {
        visit = new boolean[N][M];
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0 && !visit[i][j]) {
                    DFS(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void DFS(int x, int y) {
        visit[x][y] = true;
        for(int[] dir : DIRECTIONS) {
            int nx = x + dir[0], ny = y + dir[1];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny])
                continue;
            if(map[nx][ny] != 0)
                DFS(nx, ny);
        }
    }

    static void melt() {
        Queue<IceBerg> queue = new LinkedList<>();
        visit = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    queue.offer(new IceBerg(i, j));
                    visit[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()) {
            IceBerg now = queue.poll();
            int adjCnt = 0;
            for(int[] dir : DIRECTIONS) {
                int nx = now.x + dir[0], ny = now.y + dir[1];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny])
                    continue;
                if(map[nx][ny] == 0)
                    adjCnt++;
            }

            map[now.x][now.y] -= adjCnt;
            if(map[now.x][now.y] < 0)
                map[now.x][now.y] = 0;
        }
    }
}

class IceBerg {
    int x, y;

    IceBerg(int x, int y) {
        this.x = x;
        this.y = y;
    }
}