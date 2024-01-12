import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] C = new int[N + 1];
        for(int i = 1; i <= N; i++)
            C[i] = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> value = new ArrayList<>();
        List<Integer> cost = new ArrayList<>();
        boolean[] visit = new boolean[N + 1];
        value.add(0);
        cost.add(0);
        for(int i = 1; i <= N; i++) {
            if(visit[i])
                continue;
            int v = C[i];
            int c = 1;
            queue.offer(i);
            visit[i] = true;
            
            while(!queue.isEmpty()) {
                int now = queue.poll();
                for(int next : graph[now]) {
                    if(visit[next])
                        continue;
                    v += C[next];
                    c++;
                    queue.offer(next);
                    visit[next] = true;
                }
            }
            value.add(v);
            cost.add(c);
        }

        int[][] dp = new int[value.size()][K];
        for(int i = 1; i < value.size(); i++) {
            for(int j = 1; j < K; j++) {
                if(j < cost.get(i))
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j - cost.get(i)] + value.get(i), dp[i - 1][j]);
            }
        }
        bw.write(dp[value.size() - 1][K - 1] + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}