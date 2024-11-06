import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] minDist;
    static boolean[] visit;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][];
        for(int i = 0; i < map.length; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        int[][] locs = new int[6][2];
        int runaways = 0;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == -1) {
                    map[i][j] = 0;
                    locs[0][0] = i;
                    locs[0][1] = j;
                } else if(map[i][j] == 0) {
                    locs[++runaways][0] = i;
                    locs[runaways][1] = j;
                }
            }
        }
        
        minDist = new int[runaways + 1][runaways + 1];
        int[][] dist = new int[N][N];
        for(int i = 0; i < runaways; i++) {
            for(int[] d : dist)
                Arrays.fill(d, Integer.MAX_VALUE);
            dist[locs[i][0]][locs[i][1]] = 0;

            Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> dist[o / N][o % N]));
            pq.offer(locs[i][0] * N + locs[i][1]);
            while (!pq.isEmpty()) {
                int now = pq.poll();
                int row = now / N;
                int col = now % N;
                
                for(int[] dir : DIRECTIONS) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];
                    if(checkArea(nr, nc) && dist[nr][nc] > dist[row][col] + map[nr][nc]) {
                        dist[nr][nc] = dist[row][col] + map[nr][nc];
                        pq.offer(nr * N + nc);
                    }
                }
            }
            for(int j = 0; j <= runaways; j++)
                minDist[i][j] = minDist[j][i] = dist[locs[j][0]][locs[j][1]];
        }
        visit = new boolean[runaways + 1];
        System.out.println(dijkstra(0, 0, 0));
    }
    
    static boolean checkArea(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static int dijkstra(int now, int arrest, int sum) {
        if(arrest == visit.length - 1)
            return sum + minDist[now][0];
        
        int min = Integer.MAX_VALUE;
        for(int next = 1; next < visit.length; next++) {
            if(visit[next])
                continue;

            visit[next] = true;
            min = Math.min(min, dijkstra(next, arrest + 1, sum + minDist[now][next]));
            visit[next] = false;
        }
        return min;
    }
}