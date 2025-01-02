import java.io.*;
import java.util.*;

public class Main {
    static List<List<Node>> graph;
    static int[] dist;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, length));
            graph.get(to).add(new Node(from, length));
        }
        
        dist = new int[N + 1];
        DFS(1, 0, 0);
        DFS(findLongcontry(), 0, 0);
        DFS(findLongcontry(), 0, 0);

        for(int i = 1; i <= N; i++)
            sb.append(dist[i] + "\n");
        System.out.print(sb);
    }

    static void DFS(int now, int pre, int len) {
        for(Node next : graph.get(now)) {
            if(next.v == pre)
                continue;
            dist[next.v] = Math.max(dist[next.v], len + next.l);
            DFS(next.v, now, next.l + len);
        }
    }

    static int findLongcontry() {
        int max = 0;
        int idx = 0;
        for(int i = 1; i <= N; i++) {
            if(max < dist[i]) {
                max = dist[i];
                idx = i;
            }
        }
        return idx;
    }
}

class Node {
    int v, l;

    Node(int v, int l) {
        this.v = v;
        this.l = l;
    }
}