import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i = 1; i <= T; i++) {
            if(i != 1)
                sb.append("\n");
            sb.append("Scenario " + i + ":\n");

            int n = Integer.parseInt(br.readLine());

            parent = new int[n];
            for(int j = 0; j < n; j++)
                parent[j] = j;

            int k = Integer.parseInt(br.readLine());

            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int m = Integer.parseInt(br.readLine());

            for(int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if(find(u) == find(v))
                    sb.append(1 + "\n");
                else
                    sb.append(0 + "\n");
            }
        }
        System.out.print(sb);
    }
    
    static int find(int x) {
        return parent[x] = parent[x] == x ? x : find(parent[x]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa < fb)
            parent[fa] = fb;
        else
            parent[fb]= fa;
    }
}