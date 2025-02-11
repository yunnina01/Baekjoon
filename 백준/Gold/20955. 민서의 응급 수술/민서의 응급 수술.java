import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for(int i = 0; i < N; i++)
            parent[i] = i;
        
        int res = 0;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            if(find(u) == find(v))
                res++;
            else
                union(u, v);
        }

        for(int i = 0; i < N; i++) {
            if(parent[i] == i)
                res++;
        }
        System.out.println(res - 1);
    }

    static int find(int x) {
        return parent[x] = parent[x] == x ? x : find(parent[x]);
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(fa < fb)
            parent[fb] = fa;
        else
            parent[fa] = fb;
    }
}