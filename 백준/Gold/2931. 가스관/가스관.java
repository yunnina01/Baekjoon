import java.io.*;
import java.util.*;

public class Main {
    static final int[][] dir = {{0, 2}, {1, 3}, {1, 2}, {0, 1}, {0, 3}, {2, 3}, {0, 1, 2, 3}};
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static final char[] type = {'|', '-', '1', '2', '3', '4', '+'};
    static Queue<int[]> queue;
    static char[][] board;
    static boolean[][] visit;
    static Point start, end, res;
    static int N, M, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'M')
                    start = new Point(i, j);
                if(board[i][j] == 'Z')
                    end = new Point(i, j);
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i][j] == '.')
                    continue;
                if(board[i][j] == '+')
                    cnt += 2;
                else
                    cnt++;
            }
        }
        cnt++;

        visit = new boolean[N][M];
        queue = new ArrayDeque<>();
        visit[start.x][start.y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];
            if(nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] != 'Z' && board[nx][ny] != '.') {
                visit[nx][ny] = true;
                DFS(nx, ny);
            }
        }

        outer:
        for(int i = 0; i < 7; i++) {
            board[res.x][res.y] = type[i];
            for(int j = 0; j < 4; j++) {
                int nx = start.x + dx[j];
                int ny = start.y + dy[j];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '.' || board[nx][ny] == 'Z')
                    continue;

                queue.offer(new int[] {nx, ny, j, 2});
                while(!queue.isEmpty()) {
                    int[] now = queue.poll();
                    if(board[now[0]][now[1]] == 'Z' && now[3] >= cnt) {
                        System.out.println((res.x + 1) + " " + (res.y + 1) + " " + type[i]);
                        break outer;
                    }

                    boolean isPossible = false;
                    inner:
                    for(int k = 0; k < 7; k++) {
                        if(board[now[0]][now[1]] == type[k]) {
                            for(int d = 0; d < dir[k].length; d++) {
                                if(dir[k][d] == (now[2] + 2) % 4) {
                                    isPossible = true;
                                    break inner;
                                }
                            }
                        }
                    }
                    if(!isPossible)
                        continue;

                    for(int k = 0; k < 7; k++) {
                        if(board[now[0]][now[1]] == type[k]) {
                            if(k == 6) {
                                int nnx = now[0] + dx[now[2]];
                                int nny = now[1] + dy[now[2]];
                                if(nnx >= 0 && nnx < N && nny >= 0 && nny < M)
                                    queue.offer(new int[]{nnx, nny, now[2], now[3] + 1});
                            } else {
                                for(int d = 0; d < dir[k].length; d++) {
                                    if(dir[k][d] == (now[2] + 2) % 4)
                                        continue;
                                    int nnx = now[0] + dx[dir[k][d]];
                                    int nny = now[1] + dy[dir[k][d]];
                                    if(nnx >= 0 && nnx < N && nny >= 0 && nny < M)
                                        queue.offer(new int[]{nnx, nny, dir[k][d], now[3] + 1});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static void DFS(int x, int y) {
        if(board[x][y] == '.') {
            res = new Point(x, y);
            return;
        }
        for(int i = 0; i < 7; i++) {
            if(board[x][y] == type[i]) {
                for(int j = 0; j < dir[i].length; j++) {
                    int nx = x + dx[dir[i][j]];
                    int ny = y + dy[dir[i][j]];
                    if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        DFS(nx, ny);
                    }
                }
            }
        }
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}