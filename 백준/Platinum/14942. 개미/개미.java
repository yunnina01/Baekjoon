import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] tree;
    static int[][] parent;
    static long[] dist;
    static int[] deep, ant;
    static boolean[] visit;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        ant = new int[N];
        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
            ant[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }
        
        parent = new int[N][21];
        dist = new long[N];
        deep = new int[N];
        visit = new boolean[N];
        DFS(0, 0);
        
        for(int i = 1; i < 21; i++) {
            for(int now = 0; now < N; now++)
                parent[now][i] = parent[parent[now][i - 1]][i - 1];
        }

        sb.append("1\n");
        for(int i = 1; i < N; i++)
            sb.append(find(i) + 1 + "\n");
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static void DFS(int now, int depth) {
        deep[now] = depth;
        visit[now] = true;

        for(Node next: tree[now]) {
            if(visit[next.v])
                continue;
            parent[next.v][0] = now;
            dist[next.v] = dist[now] + next.w;
            DFS(next.v, depth + 1);
        }
    }

    static int find(int x) {
        long cost = ant[x];
        for(int i = 20; i >= 0; i--) {
            int jump = 1 << i;
            long diff = dist[x] - dist[parent[x][i]];
            if(jump > deep[x] || cost < diff)
                continue;
            x = parent[x][i];
            cost -= diff;
        }
        return x;
    }
}

class Node {
    int v;
    long w;

    Node(int v, long w){
        this.v = v;
        this.w = w;
    }
}