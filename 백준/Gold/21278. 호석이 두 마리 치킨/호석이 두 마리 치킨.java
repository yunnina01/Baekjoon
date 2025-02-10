import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1_000_000;
    static int[][] dist;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            dist[A][B] = dist[B][A] = 1;
        }
        floyd();

        int idx = -1, idx2 = -1;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                int sum = 0;
                for(int k = 0; k < N; k++) {
                    if(k == i || k == j)
                        continue;
                    int curDist = Math.min(dist[k][i], dist[k][j]);
                    sum += curDist * 2;
                }

                if(sum < res) {
                    idx = i;
                    idx2 = j;
                    res = sum;
                }
            }
        }
        System.out.println(++idx + " " + ++idx2 + " " + res);
    }

    static void floyd() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    if(dist[j][i] + dist[i][k] < dist[j][k])
                        dist[j][k] = dist[j][i] + dist[i][k];
                }
            }
        }
    }
}