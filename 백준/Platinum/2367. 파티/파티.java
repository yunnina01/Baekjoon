import java.io.*;
import java.util.*;

public class Main {
    static int[][] capacity, flows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        capacity = new int[N + D + 2][N + D + 2];
        flows = new int[N + D + 2][N + D + 2];
        for(int i = 1; i <= N; i++)
            capacity[0][i] = K;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= D; i++)
            capacity[N + i][N + D + 1] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int Z = Integer.parseInt(st.nextToken());
            for(int j = 0; j < Z; j++)
                capacity[i + 1][N + Integer.parseInt(st.nextToken())] = 1;
        }

        int res = 0;
        while(true) {
            int newFlow = fordFulkerson(0, Integer.MAX_VALUE, new boolean[N + D + 2]);
            if(newFlow == 0)
                break;
            res += newFlow;
        }
        System.out.println(res);
    }

    static int fordFulkerson(int n, int flow, boolean[] visited) {
        if(n == capacity.length - 1)
            return flow;
        visited[n] = true;

        int originFlow = flow;
        for(int i = 0; i < capacity[n].length; i++) {
            if(!visited[i] && capacity[n][i] - flows[n][i] > 0) {
                int newFlow = fordFulkerson(i, Math.min(flow, capacity[n][i] - flows[n][i]), visited);
                flow -= newFlow;
                flows[n][i] += newFlow;
                flows[i][n] -= newFlow;
            }
        }
        return originFlow - flow;
    }
}