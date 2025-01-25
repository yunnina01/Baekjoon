import java.io.*;
import java.util.*;

public class Main {
    final static int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][][] data = new int[N + 1][N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                data[0][i][j] = Integer.parseInt(st.nextToken());
                if(i != j && data[0][i][j] == 0)
                    data[0][i][j] = INF;
            }
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++)
                    data[k][i][j] = Math.min(data[k - 1][i][j], data[k - 1][i][k] + data[k - 1][k][j]);
            }
        }

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append((data[C - 1][s][e] == INF ? -1 : data[C - 1][s][e]) + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}