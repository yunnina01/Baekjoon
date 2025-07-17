import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> edge;
    static int[] map, visit, prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0)
                break;

            edge = new ArrayList<>();
            map = new int[n];
            prices = new int[n];
            for(int i = 0; i < n; i++) {
                edge.add(new ArrayList<>());

                st = new StringTokenizer(br.readLine());
                char room = st.nextToken().charAt(0);
                if(room == 'E')
                    map[i] = 0;
                else if(room == 'L')
                    map[i] = 1;
                else
                    map[i] = 2;

                prices[i] = Integer.parseInt(st.nextToken());
                int len = st.countTokens();
                for(int j = 0; j < len - 1; j++)
                    edge.get(i).add(Integer.parseInt(st.nextToken()) - 1);
            }

            visit = new int[n];
            Arrays.fill(visit, -1);
            sb.append(BFS(n));
        }
        System.out.print(sb);
    }

    static String BFS(int N) {
        Queue<Adventurer> queue = new LinkedList<>();
        if(map[0] != 2) {
            visit[0] = prices[0];
            queue.offer(new Adventurer(0, prices[0]));
        }
        while(!queue.isEmpty()) {
            Adventurer now = queue.poll();
            if(now.node == N - 1)
                return "Yes\n";
            if(now.cost < visit[now.node])
                continue;
            for(int next : edge.get(now.node)) {
                int nc = getNextCost(next, now.cost);
                if(nc >= 0  && visit[next] < nc) {
                    queue.offer(new Adventurer(next, nc));
                    visit[next] = nc;
                }
            }
        }
        return "No\n";
    }

    static int getNextCost(int N, int cost) {
        if(map[N] == 2)
            return cost - prices[N];
        else {
            if(prices[N] > cost)
                return prices[N];
            return cost;
        }
    }
}

class Adventurer {
    int node, cost;

    Adventurer(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}