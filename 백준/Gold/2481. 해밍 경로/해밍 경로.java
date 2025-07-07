import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static List<Edge> routes;
    static Stack<Integer> stack;
    static int[] seq;
    static int N, K;
    static boolean canMove;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        routes = new ArrayList<>();
        Edge first = null;
        for(int i = 0; i < N; i++) {
            routes.add(new Edge(Integer.parseInt(br.readLine(), 2), i));
            if(i == 0)
                first = routes.get(0);
        }
        Collections.sort(routes);

        int M = Integer.parseInt(br.readLine());

        seq = new int[N];
        Arrays.fill(seq, -1);
        BFS(first);
        for(int i = 0; i < M; i++) {
            int now = Integer.parseInt(br.readLine()) - 1;
            findRoute(now);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void BFS(Edge first) {
        Queue<Edge> queue = new LinkedList<>();
        queue.offer(first);
        seq[first.idx] = -2;
        while(!queue.isEmpty()) {
            Edge now = queue.poll();
            int bit = 1;
            for(int i = 0; i < K; i++) {
                int next = now.num ^ bit;
                int BSans = binarySearch(next);
                if(BSans != -1){
                    int nextIdx = routes.get(BSans).idx;
                    if(seq[nextIdx] == -1) {
                        queue.offer(new Edge(next, nextIdx));
                        seq[nextIdx] = now.idx;
                    }
                }
                bit <<= 1;
            }
        }
    }

    static int binarySearch(int target) {
        int left = 0;
        int right = routes.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(routes.get(mid).num == target)
                return mid;
            else if(routes.get(mid).num < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }


    static void findRoute(int des){
        stack = new Stack<>();
        canMove = true;
        recur(des);
        if(!canMove) {
            sb.append(-1);
            return;
        }
        while(!stack.isEmpty())
            sb.append((stack.pop() + 1) + " ");
    }

    static void recur(int idx) {
        stack.push(idx);
        if(idx == 0)
            return;
        if(seq[idx] == -1) {
            canMove = false;
            return;
        }
        recur(seq[idx]);
    }
}

class Edge implements Comparable<Edge> {
    int num, idx;

    Edge(int num, int idx) {
        this.num = num;
        this.idx = idx;
    }

    @Override
    public int compareTo(Edge O) {
        return this.num - O.num;
    }
}