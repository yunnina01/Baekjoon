import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] graph = new int[N][3];
        parent = new int[2 * N + 10];
        for(int i = 0; i < 2 * N + 10; i++)
            parent[i] = i;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int p3 = Integer.parseInt(st.nextToken());
            int p4 = Integer.parseInt(st.nextToken());

            union(p1, p2);
            union(p3, p4);
            graph[i][0] = c;
            graph[i][1] = p1;
            graph[i][2] = p3;
        }

        Arrays.sort(graph, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        int res = 0;
        for(int i = 0; i < N; i++) {
            int c = graph[i][0];
            int a = graph[i][1];
            int b = graph[i][2];

            if(find(a) == find(b))
                continue;
            union(a, b);
            res += c;
        }
        System.out.println(res);
    }

    static int find(int x) {
        return parent[x] = parent[x] == x ? x : find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b)
            parent[b] = a;
    }
}