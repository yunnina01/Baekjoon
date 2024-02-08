import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N == 2) {
            System.out.println(0);
            return;
        }

        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.add(new Node(A, B, C));
        }
        graph.sort((o1, o2) -> {
            if(o1.w != o2.w)
                return o1.w - o2.w;
            else if(o1.u != o2.u)
                return o1.u - o2.u;
            return o1.v - o2.v;
        });

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++)
            parent[i] = i;
        int cnt = 0;
        long res = 0;
        for(Node now : graph) {
            if(!union(now.u, now.v))
                continue;
            res += now.w;
            if(++cnt == N - 2)
                break;
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y)
            return false;

        parent[Math.max(x, y)] = Math.min(x, y);
        return true;
    }

    static int find(int x) {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }
}

class Node {
    int u, v, w;

    Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}