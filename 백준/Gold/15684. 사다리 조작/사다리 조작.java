import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visit;
    static int N, M, H, res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        visit = new boolean[H][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            visit[a - 1][b - 1] = true;            
        }

        res = 4;
        DFS(0, 0, 0);
        System.out.println(res <= 3 ? res + "" : "-1");
    }

    static void DFS(int cnt, int x, int y) {
        if(res <= cnt)
            return;
        if(check()) {
            res = Math.min(res, cnt);
            return;
        }
        if(cnt == 3)
            return;

        for(int i = x; i < H; i++) {
            int k = (i == x) ? y : 0;
            for(int j = k; j < N - 1; j++) {
                if(!visit[i][j]) {
                    visit[i][j] = true;
                    DFS(cnt + 1, i, j + 2);
                    visit[i][j] = false;
                }
            }
        }
    }

    static boolean check() {
        for(int i = 0; i < N; i++) {
            int tmp = i;
            for(int j = 0; j < H; j++) {
                if(visit[j][tmp])
                    tmp++;
                else if(tmp > 0 && visit[j][tmp - 1])
                    tmp--;
            }
            if(tmp != i)
                return false;
        }
        return true;
    }
}