import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] checkers = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            checkers[i][0] = Integer.parseInt(st.nextToken());
            checkers[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] crossings = new int[N * N][2];
        for(int i = 0, idx = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                crossings[idx][0] = checkers[i][0];
                crossings[idx++][1] = checkers[j][1];
            }
        }

        int[] dists = new int[N];
        for(int i = 1; i <= N; i++) {
            int minDist = Integer.MAX_VALUE;
            for(int cr = 0; cr < N * N; cr++) {
                for(int ck = 0; ck < N; ck++)
                    dists[ck] = Math.abs(checkers[ck][0] - crossings[cr][0]) + Math.abs(checkers[ck][1] - crossings[cr][1]);

                Arrays.sort(dists);

                int sum = 0;
                for(int j = 0; j < i; j++)
                    sum += dists[j];

                minDist = Math.min(minDist, sum);
            }
            sb.append(minDist + " ");
        }
        System.out.println(sb);
    }
}