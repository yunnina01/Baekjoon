import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> edges;
    static int N, L;

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for(int i = 0; i < N + L; i++)
            edges.add(new ArrayList<>());

        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            while(true) {
                int station = Integer.parseInt(st.nextToken()) - 1;

                if(station == -2)
                    break;
                edges.get(N + i).add(station);
                edges.get(station).add(N + i);
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        System.out.println(BFS(start, end));
    }

    static int BFS(int start, int end) {
        if(start == end)
            return 0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N + L];
        queue.offer(start);
        visit[start] = true;
        int cnt = 0;

        while(!queue.isEmpty()) {
            int len = queue.size();
            if(queue.peek() >= N)
                cnt++;
            for(int i = 0; i < len; i++) {
                int now = queue.poll();
                if(now == end)
                    return cnt - 1;
                for(int next : edges.get(now)) {
                    if(!visit[next]) {
                        queue.offer(next);
                        visit[next] = true;
                    }
                }
            }
        }
        return -1;
    }
}