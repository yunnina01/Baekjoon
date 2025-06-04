import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<Point> jQueue, fQueue;
    static char[][] map;
    static boolean[][] visit;
    static int R, C;

    static int BFS() {
        int cnt = 0;
        while(!jQueue.isEmpty()){
            int fLen = fQueue.size();
            for(int i = 0; i < fLen; i++) {
                Point fNow = fQueue.poll();
                for(int[] dir : DIRECTIONS) {
                    int ny = fNow.y + dir[0];
                    int nx = fNow.x + dir[1];
                    if(!isOuter(ny, nx) && map[ny][nx] == '.') {
                        fQueue.offer(new Point(ny, nx));
                        map[ny][nx] = 'F';
                    }
                }
            }

            int jLen = jQueue.size();
            for(int i = 0; i < jLen; i++) {
                Point jNow = jQueue.poll();
                for(int[] dir : DIRECTIONS) {
                    int ny = jNow.y + dir[0];
                    int nx = jNow.x + dir[1];
                    if(isOuter(ny, nx))
                        return cnt + 1;

                    if(map[ny][nx] == '.' && !visit[ny][nx]) {
                        jQueue.offer(new Point(ny, nx));
                        visit[ny][nx] = true;
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    static boolean isOuter(int y, int x) {
        return y < 0 || y >= R || x < 0 || x >= C;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        jQueue = new LinkedList<>();
        fQueue = new LinkedList<>();
        map = new char[R][C];
        visit = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            String row = br.readLine();
            for(int j = 0; j < C; j++) {
                char cell = row.charAt(j);
                if(cell == 'J'){
                    jQueue.offer(new Point(i, j));
                    visit[i][j] = true;
                    cell = '.';
                } else if(cell == 'F')
                    fQueue.offer(new Point(i, j));
                map[i][j] = cell;
            }
        }

        int res = BFS();
        System.out.println(res == -1 ? "IMPOSSIBLE" : res);
    }
}

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}