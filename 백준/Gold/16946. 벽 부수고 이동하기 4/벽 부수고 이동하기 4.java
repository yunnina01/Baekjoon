import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] isWall = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < M; j++) {
                if(row.charAt(j) == '1')
                    isWall[i][j] = true;
            }
        }

        Queue<Point> queue = new ArrayDeque<>();
        int[] parent = new int[N * M];
        int[] blankCnt = new int[N * M];
        boolean[][] visit = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visit[i][j] || isWall[i][j])
                    continue;
                queue.offer(new Point(i, j));
                parent[i * M + j] = i * M + j;
                visit[i][j] = true;
                
                int cnt = 1;
                while(!queue.isEmpty()) {
                    Point now = queue.poll();
                    for (int[] dir : DIRECTIONS) {
                        int nx = now.x + dir[0], ny = now.y + dir[1];
                        if(nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny] || isWall[nx][ny])
                            continue;

                        queue.offer(new Point(nx, ny));
                        parent[nx * M + ny] = i * M + j;
                        visit[nx][ny] = true;
                        cnt++;
                    }
                }
                blankCnt[i * M + j] = cnt;
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(isWall[i][j]) {
                    set.clear();
                    for(int[] dir : DIRECTIONS) {
                        int nx = i + dir[0], ny = j + dir[1];
                        if(nx < 0 || nx >= N || ny < 0 || ny >= M || isWall[nx][ny])
                            continue;
                        set.add(parent[nx * M + ny]);
                    }
                    
                    int tmp = 1;
                    for(int e : set)
                        tmp += blankCnt[e];
                    sb.append(tmp % 10);
                } else
                    sb.append(0);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}