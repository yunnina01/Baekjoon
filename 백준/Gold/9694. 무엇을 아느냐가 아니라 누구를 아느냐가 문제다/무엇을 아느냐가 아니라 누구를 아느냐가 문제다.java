import java.io.*;
import java.util.*;

public class Main {
    static List<List<Edge>> elist;
    static Stack<Integer> stack;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("Case #" + t + ": ");

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            elist = new ArrayList<>();
            for(int i = 0; i < M; i ++)
                elist.add(new ArrayList<>());

            for(int i = 0 ; i < N ; i ++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                elist.get(x).add(new Edge(y, z));
                elist.get(y).add(new Edge(x, z));
            }
            
            stack = new Stack<>();
            seq = new int[M];
            if(dijkstra(M)) {
                getRoot(M - 1);
                while(!stack.isEmpty())
                    sb.append(stack.pop() + " ");
                sb.append("\n");
            } else
                sb.append(-1 + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static boolean dijkstra(int M) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dis = new int[M];
        Arrays.fill(dis, Integer.MAX_VALUE);
        pq.offer(new Edge(0, 0));
        dis[0] = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(now.cost > dis[now.node])
                continue;
            if(now.node == M - 1)
                return true;
            for(Edge next : elist.get(now.node)){
                if(dis[next.node] > now.cost + next.cost) {
                    dis[next.node] = now.cost + next.cost;
                    pq.offer(new Edge(next.node, dis[next.node]));
                    seq[next.node] = now.node;
                }
            }
        }
        return false;
    }

    static void getRoot(int x) {
        if(x == 0) {
            stack.push(x);
            return;
        }
        stack.push(x);
        getRoot(seq[x]);
    }
}

class Edge implements Comparable<Edge> {
    int node, cost;

    Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}