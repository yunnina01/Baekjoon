import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0)
                break;

            int[][] map = new int[N + 1][M + 1];
            int res = 0;
            for(int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= M; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if(num == 0)
                        continue;
                    res = Math.max(res, map[i][j] = Math.min(Math.min(map[i][j - 1], map[i - 1][j]), map[i - 1][j - 1]) + 1);
                }
            }
            sb.append(res + "\n");
        }
        System.out.print(sb);
    }
}