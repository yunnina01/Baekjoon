import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static int[] left, right;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            graph = new ArrayList<>();
            for(int i = 0; i < 1001; i++)
                graph.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            while(M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
            }

            int res = 0;
            left = new int[1001];
            right = new int[1001];
            visit = new boolean[1001];
            Arrays.fill(left, -1);
            Arrays.fill(right, -1);

            for(int i = 0; i < N; i++) {
                Arrays.fill(visit, false);
                if(DFS(i))
                    res++;
            }
            sb.append(res + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean DFS(int x) {
        visit[x] = true;
        for(int now : graph.get(x)) {
            if(right[now] == -1 || (!visit[right[now]] && DFS(right[now]))) {
                left[x] = now;
                right[now] = x;
                return true;
            }
        }
        return false;
    }
}