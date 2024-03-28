import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int v = (N + 1) * 2;
        int[][] capacity = new int[v][v];
        int[][] flow = new int[v][v];
        for(int i = 2; i < v; i += 2)
            capacity[i][i + 1] = 1;
        
        final int INF = 100000;
        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            capacity[s * 2 + 1][e * 2] = INF;
            capacity[e * 2 + 1][s * 2] = INF;
        }

        int source = 3;
        int sink = 4;
        int res = 0;
        while(true) {
            Queue<Integer> queue = new LinkedList<>();
            int[] parent = new int[v];
            Arrays.fill(parent, -1);
            parent[source] = source;
            queue.offer(source);

            while(!queue.isEmpty() && parent[sink] == -1) {
                int now = queue.poll();
                for(int next = 2; next < v; next ++) {
                    if(parent[next] == -1 && capacity[now][next] - flow[now][next] > 0) {
                        queue.offer(next);
                        parent[next] = now;
                    }
                }
            }
            if(parent[sink] == -1)
                break;
            
            int minFlow = Integer.MAX_VALUE;
            for(int i = sink; i != source; i = parent[i])
                minFlow = Math.min(minFlow, capacity[parent[i]][i] - flow[parent[i]][i]);
            for(int i = sink; i != source; i = parent[i]) {
                flow[parent[i]][i] += minFlow;
                flow[i][parent[i]] -= minFlow;
            }
            res += minFlow;
        }
        System.out.println(res);
    }    
}