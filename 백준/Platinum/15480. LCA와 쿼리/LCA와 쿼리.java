import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> tree;
    static int[][] parent;
    static int[] depth;
    static int N, M, maxDepth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            tree.add(new ArrayList<>());

        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        maxDepth = 0;
        for(int i = 1; i <= N; i <<= 1)
            maxDepth++;
        parent = new int[N + 1][maxDepth];
        depth = new int[N + 1];

        findParent(1, 1);
        findAncester();

        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int ap = lca(u, v);
            int bp = lca(r, v);
            int cp = lca(r, u);
            sb.append((depth[ap] < depth[bp] ? (depth[cp] < depth[bp] ? bp : cp) : (depth[ap] < depth[cp] ? cp : ap)) +"\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void findParent(int node, int d) {
        depth[node] = d;
        for(int child : tree.get(node)){
            if(depth[child] == 0) {
                findParent(child, d + 1);
                parent[child][0] = node;
            }
        }
    }

    static void findAncester(){
        for(int i = 1; i < maxDepth; i++) {
            for(int j = 1; j <= N; j++)
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
        }
    }

    static int lca(int a, int b) {
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = maxDepth - 1; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b])
                a = parent[a][i];
        }

        if(a == b)
            return a;
        else {
            for(int i = maxDepth - 1; i >= 0; i--) {
                if(parent[a][i] != parent[b][i]) {
                    a = parent[a][i];
                    b = parent[b][i];
                }
            }
            return parent[a][0];
        }
    }
}