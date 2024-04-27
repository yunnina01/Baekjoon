import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static final int INF = 987654321;
    static List<List<Edge>> edges;
    static int source = 0, sink = 1, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            size = n * m + 2;
            
            edges = new ArrayList<>();
            for(int i = 0; i < size; i++)
                edges.add(new ArrayList<>());
            int[][] board = new int[n][m];

            int total = 0;
            int idx = 2;
            boolean flag = true;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    total += board[i][j];

                    if(flag) {
                        Edge e = new Edge(source, idx, board[i][j]);
                        Edge rev = new Edge(idx, source, 0);
                        e.rev = rev;
                        rev.rev = e;

                        edges.get(source).add(e);
                        edges.get(idx).add(rev);
                    } else {
                        Edge e = new Edge(idx, sink, board[i][j]);
                        Edge rev = new Edge(sink, idx, 0);
                        e.rev = rev;
                        rev.rev = e;

                        edges.get(idx).add(e);
                        edges.get(sink).add(rev);
                    }
                    idx++;
                    flag = !flag;
                }
                if(m % 2 == 0)
                    flag = !flag;
            }

            int[] changeIdx = {m, -m, 1, -1};
            idx = 2;
            flag = true;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(flag) {
                        for(int k = 0; k < 4; k++) {
                            int bi = i + DIRECTIONS[k][0];
                            int bj = j + DIRECTIONS[k][1];
                            if(bi < 0 || n <= bi || bj < 0 || m <= bj)
                                continue;

                            int blueIdx = idx + changeIdx[k];
                            Edge e = new Edge(idx, blueIdx, INF);
                            Edge rev = new Edge(blueIdx, idx, 0);
                            e.rev = rev;
                            rev.rev = e;

                            edges.get(idx).add(e);
                            edges.get(blueIdx).add(rev);
                        }
                    }
                    flag = !flag;
                    idx++;
                }
                if(m % 2 == 0) 
                    flag = !flag;
            }

            int maxFlow = getMaxFlow();
            bw.write(total - maxFlow + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static int getMaxFlow() {
        int maxFlow = 0;

        while(true) {
            Edge[] parent = new Edge[size];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);

            while(!queue.isEmpty()) {
                int now = queue.poll();
                if(now == sink)
                    break;
                for(Edge next : edges.get(now)) {
                    if(parent[next.e] == null && next.capa - next.flow > 0) {
                        queue.offer(next.e);
                        parent[next.e] = next;
                        if(next.e == sink)
                            break;
                    }
                }
            }
            if(parent[sink] == null)
                break;

            int minFlow = Integer.MAX_VALUE;
            for(int i = sink; i != source; i = parent[i].s)
                minFlow = Math.min(minFlow, parent[i].capa - parent[i].flow);
            for(int i = sink; i != source; i = parent[i].s) {
                parent[i].flow += minFlow;
                parent[i].rev.flow -= minFlow;
            }
            maxFlow += minFlow;
        }
        return maxFlow;
    }
}

class Edge {
    Edge rev;
    int s, e, capa, flow;

    Edge(int s, int e, int capa) {
        this.s = s;
        this.e = e;
        this.capa = capa;
        this.flow = 0;
    }
}