import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int source = 0;
        int sink = N + M + 1;
        int bridge = sink + 1;
        
        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0; i <= bridge; i++)
            edges.add(new ArrayList<>());
        int[][] capacity = new int[bridge + 1][bridge + 1];
        int[][] flow = new int[bridge + 1][bridge + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j = 0; j < cnt; j++) {
                int idx = Integer.parseInt(st.nextToken());
                edges.get(i).add(idx + N);
                edges.get(idx + N).add(i);
                capacity[i][idx + N] = 1;
            }
        }

        edges.get(source).add(bridge);
        edges.get(bridge).add(source);
        capacity[source][bridge] = K;

        for(int i = 1; i <= N; i++) {
            edges.get(source).add(i);
            edges.get(i).add(source);
            capacity[source][i] = 1;

            edges.get(bridge).add(i);
            edges.get(i).add(bridge);
            capacity[bridge][i] = K;
        }
        for(int i = N + 1; i < sink; i++) {
            edges.get(i).add(sink);
            edges.get(sink).add(i);
            capacity[i][sink] = 1;
        }

        int res = 0;
        while(true) {
            Queue<Integer> queue = new LinkedList<>();
            int[] parent = new int[bridge + 1];
            Arrays.fill(parent, -1);
            parent[source] = source;
            queue.offer(source);

            while(!queue.isEmpty() && parent[sink] == -1) {
                int now = queue.poll();
                for(int next : edges.get(now)) {
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