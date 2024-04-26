import java.io.*;
import java.util.*;

public class Main {
    static final int SIZE = 103;
    static List<List<Integer>> edges;
    static int[][] capacity, flow;
    static int source = 101, sink = 102, bias = 50;
    static int N, rowSum, colSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        int[] rows = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            rows[i] = Integer.parseInt(st.nextToken());
            rowSum += rows[i];
        }
        int[] cols = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            cols[i] = Integer.parseInt(st.nextToken());
            colSum += cols[i];
        }

        edges = new ArrayList<>();
        for(int i = 0; i < SIZE; i++)
            edges.add(new ArrayList<>());
        
        capacity = new int[SIZE][SIZE];
        for(int i = 1; i <= N; i++) {
            edges.get(source).add(i);
            capacity[source][i] = rows[i];
            edges.get(i + bias).add(sink);
            capacity[i + bias][sink] = cols[i];
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                edges.get(i).add(j + bias);
                edges.get(j + bias).add(i);
            }
        }

        int left = 0, right = 10000;
        int res = -1;
        while(left <= right) {
            int mid = (left + right) >> 1;
            if(BFS(mid)) {
                res = mid;
                right = mid - 1;
            } else
                left = mid + 1;
        }
        sb.append(res + "\n");

        BFS(res);
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++)
                sb.append(flow[i][j + bias] + " ");
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    
    static boolean BFS(int x) {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++)
                capacity[i][j + bias] = x;
        }
        flow = new int[SIZE][SIZE];

        int res = 0;
        while(true) {
            Queue<Integer> queue = new LinkedList<>();
            int[] parent = new int[SIZE];
            Arrays.fill(parent, -1);
            parent[source] = source;
            queue.offer(source);

            while(!queue.isEmpty()) {
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
        return rowSum == colSum && rowSum == res;
    }
}