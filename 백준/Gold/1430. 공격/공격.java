import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> edges;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        double R = Double.parseDouble(st.nextToken());
        double D = Double.parseDouble(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        Pair[] towers = new Pair[N + 1];
        edges = new ArrayList<>();

        edges.add(new ArrayList<>());
        towers[0] = new Pair(X, Y);

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            towers[i] = new Pair(X, Y);

            edges.add(new ArrayList<>());
            for(int j = 0; j < i; j++) {
                double d = distance(towers[i], towers[j]);
                if(d > R)
                    continue;
                edges.get(i).add(j);
                edges.get(j).add(i);
            }
        }

        bw.write(String.format("%.2f\n", BFS(D)));
        br.close();
        bw.flush();
        bw.close();
    }

    static double BFS(double damage) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N + 1];
        queue.offer(0);
        visit[0] = true;

        double time = 1;
        double res = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int now = queue.poll();
                for(int next : edges.get(now)) {
                    if(visit[next])
                        continue;
                    visit[next] = true;
                    res += damage * time;
                    queue.offer(next);
                }
            }
            time /= 2;
        }
        return res;
    }

    static double distance(Pair p1, Pair p2) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
