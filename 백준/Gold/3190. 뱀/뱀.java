import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;
    static int N, K, L, d = 0;
    static int[][] map;
    static Map<Integer, String> moveInfo;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        moveInfo = new HashMap<>();
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            moveInfo.put(time, direction);
        }
        solve();
    }

    static void solve() throws IOException {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int time = 0, x = 0, y = 0;

        while(true) {
            int nx = x + dx[d], ny = y + dy[d];
            time++;

            if(nx < 0 || nx >= N || ny < 0 || ny >= N)
                break;
            if(q.contains(ny * N + nx))
                break;
            
            if(map[ny][nx] == 1) {
                map[ny][nx] = 0;
                q.offer(ny * N + nx);
            } else {
                q.offer(ny * N + nx);
                q.poll();
            }

            if(moveInfo.containsKey(time)) {
                String direction = moveInfo.get(time);
                if(direction.equals("L")) {
                    d -= 1;
                    if(d == -1)
                        d = 3;
                } else {
                    d += 1;
                    if(d == 4)
                        d = 0;
                }
            }
            x = nx;
            y = ny;
        }
        bw.write(time + "\n");
        bw.flush();
        bw.close();
    }
}