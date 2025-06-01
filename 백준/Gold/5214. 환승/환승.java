import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> edgeList;
    static int N, M, K;

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for(int i = 0; i < N + M; i++)
            edgeList.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) {
                int tube = Integer.parseInt(st.nextToken()) - 1;
                edgeList.get(N + i).add(tube);
                edgeList.get(tube).add(N + i);
            }
        }
        System.out.println(BFS());
    }

    static int BFS() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N + M];
        queue.offer(0);
        visit[0] = true;
        int cnt = 1;
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i = 0; i < len; i ++) {
                int now = queue.poll();
                if(now == N - 1)
                    return cnt;
                for(int next : edgeList.get(now)) {
                    if(!visit[next]){
                        queue.offer(next);
                        visit[next] = true;
                    }
                }
            }
            if(!queue.isEmpty() && queue.peek() >= N)
                cnt--;
            cnt++;
        }
        return -1;
    }
}