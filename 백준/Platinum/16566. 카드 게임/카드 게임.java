import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> cards = new ArrayList<>();
        for(int i = 0; i < M; i++)
            cards.add(Integer.parseInt(st.nextToken()));
        cards.add(N + 1);
        Collections.sort(cards);

        st = new StringTokenizer(br.readLine());
        parent = new int[N + 2];
        for(int i = 1; i <= N + 1; i++)
            parent[i] = i;
        for(int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());

            int idx = Collections.binarySearch(cards, n + 1);
            if(idx < 0)
                idx = -idx - 1;
            int m1 = find(cards.get(idx));

            idx = Collections.binarySearch(cards, m1 + 1);
            if(idx < 0)
                idx = -idx - 1;
            int m2 = find(cards.get(idx));

            union(m1, m2);
            sb.append(m1 + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static void union(int x, int y) {
        if(x == y)
            return;
        parent[Math.min(x, y)] = Math.max(x, y);
    }

    static int find(int x) {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }
}