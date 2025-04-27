import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int i = 0; i <= N; i++)
                graph.add(new ArrayList<>());
            
            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(u).add(new Node(v, c, d));
            }

            int[][] dp = new int[N + 1][M + 1];
            for(int i = 0; i <= N; i++)
                Arrays.fill(dp[i], INF);
            dp[1][0] = 0;
            for(int i = 0; i <= M; i++) {
                for(int j = 1; j <= N; j++) {
                    if(dp[j][i] == INF)
                        continue;
                    for(Node next : graph.get(j)) {
                        if(next.c + i > M)
                            continue;
                        dp[next.v][next.c + i] = Math.min(dp[next.v][next.c + i], dp[j][i] + next.d);
                    }
                }
            }
            int res = INF;
            for(int i = 0; i <= M; i++) {
                if(dp[N][i] < res)
                    res = dp[N][i];
            }
            System.out.println(res != INF ? res : "Poor KCM");
        }
    }
}

class Node {
    int v, c, d;

    Node(int v, int c, int d) {
        this.v = v;
        this.c = c;
        this.d = d;
    }
}