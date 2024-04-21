import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> seatList;
    static int[] seatIdx;
    static boolean[] visit;
    static int[] dx = {1, 1, 0, 0, -1, -1}, dy = {-1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int C = Integer.parseInt(br.readLine());

        while(C-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] classroom = new int[N][M];
            int seatCnt = 0;
            for(int i = 0; i < N; i++) {
                String line = br.readLine();
                for(int j = 0; j < M; j++) {
                    char c = line.charAt(j);
                    if(c == 'x')
                        classroom[i][j] = 1;
                    else
                        seatCnt++;
                }
            }

            seatList = new ArrayList<>();
            for(int i = 0; i <= N * M; i++)
                seatList.add(new ArrayList<>());
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(classroom[i][j] == 1)
                        continue;
                    for(int k = 0; k < 6; k++) {
                        int nx = i + dx[k], ny = j + dy[k];
                        if(nx < 0 || nx >= N || ny < 0 || ny >= M || classroom[nx][ny] == 1)
                            continue;
                        seatList.get(i * M + j).add(nx * M + ny);
                    }
                }
            }

            seatIdx = new int[N * M];
            visit = new boolean[N * M];
            Arrays.fill(seatIdx, -1);
            int maxMatching = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j += 2) {
                    if(classroom[i][j] == 1)
                        continue;
                    Arrays.fill(visit, false);
                    if(DFS(i * M + j))
                        maxMatching++;
                }
            }
            bw.write(seatCnt - maxMatching + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean DFS(int x) {
        if(visit[x])
            return false;
        visit[x] = true;
        for(int now : seatList.get(x)) {
            if(seatIdx[now] == -1 || DFS(seatIdx[now])) {
                seatIdx[now] = x;
                return true;
            }
        }
        return false;
    }
}