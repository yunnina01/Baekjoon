import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        
        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            edges.add(new ArrayList<>());
        int[][] capacity = new int[N + 1][N + 1];
        int[][] flow = new int[N + 1][N + 1];

        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edges.get(s).add(e);
            edges.get(e).add(s);
            capacity[s][e] = 1;
        }

        int src = 1;
        int sink = 2;
        int res = 0;
        while(true) {
            Queue<Integer> queue = new LinkedList<>();
            int[] parent = new int[N + 1];
            Arrays.fill(parent, -1);
            parent[src] = src;
            queue.offer(src);

            while(!queue.isEmpty() && parent[sink] == -1) {
                int now = queue.poll();
                for(int next : edges.get(now)) {
                    if(capacity[now][next] - flow[now][next] > 0 && parent[next] == -1) {
                        queue.offer(next);
                        parent[next] = now;
                    }
                }
            }
            if(parent[sink] == -1)
                break;
            
            int minFlow = Integer.MAX_VALUE;
            for(int i = sink; i != src; i = parent[i])
                minFlow = Math.min(minFlow, capacity[parent[i]][i] - flow[parent[i]][i]);
            for(int i = sink; i != src; i = parent[i]) {
                flow[parent[i]][i] += minFlow;
                flow[i][parent[i]] -= minFlow;
            }
            res += minFlow;
        }
        System.out.println(res);
    }
}