import java.io.*;
import java.util.*;;

public class Main {
    static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int INF = 100000;
    static final long CIPHER = 100000L;
    static List<Integer>[] edges;
    static HashMap<Long, Integer> capacity, flow;
    static int N, M;
    static int S, T, source, sink, size;
    static boolean notDefense;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'K')
                    source = i * M + j;
                else if(map[i][j] == 'H')
                    sink = i * M + j;
            }
        }

        graphModeling(map);
        bw.write(networkFlow() + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static void graphModeling(char[][] map) {
        int half = N * M;
        size = half * 2 + 2;
        S = size - 2;
        T = size - 1;

        edges = new ArrayList[size];
        for(int i = 0; i < size; i++)
            edges[i] = new ArrayList<>();

        capacity = new HashMap<>();
        flow = new HashMap<>();
        linker(S, source, INF);
        linker(sink + half, T, INF);
        for(int i = 0; i < half; i++)
            linker(i + half, i, 1);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == '#')
                    continue;

                boolean adjacent = false;
                if(map[i][j] == 'K')
                    adjacent = true;
                
                int now = i * M + j;
                for(int[] dir : DIRECTIONS) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == '#')
                        continue;
                    if(adjacent && map[ni][nj] == 'H')
                        notDefense = true;
                    
                    int next = ni * M + nj;
                    linker(now, next + half, INF);
                    linker(next, now + half, INF);
                }
            }
        }
    }

    static void linker(int from, int to, int cap) {
        long index = CIPHER * from + to;
        long rev = CIPHER * to + from;
        capacity.put(index, cap);
        capacity.put(rev, 0);
        flow.put(index, 0);
        flow.put(rev, 0);

        edges[from].add(to);
        edges[to].add(from);
    }

    static int networkFlow(){
        if((N == 1 && M == 1) || notDefense)
            return -1;

        int[] parent = new int[size];
        int res = 0;
        while(true){
            Queue<Integer> queue = new LinkedList<>();
            Arrays.fill(parent, -1);
            queue.offer(S);

            while(!queue.isEmpty()) {
                int now = queue.poll();
                for(int next: edges[now]) {
                    long index = CIPHER * now + next;
                    if(capacity.get(index) <= flow.get(index))
                        continue;
                    if(parent[next] != -1)
                        continue;

                    queue.offer(next);
                    parent[next] = now;
                }
            }
            if(parent[T] == -1)
                break;

            int minFlow = Integer.MAX_VALUE;
            for(int i = T; i != S; i = parent[i]) {
                long index = CIPHER * parent[i] + i;
                minFlow = Math.min(minFlow, capacity.get(index) - flow.get(index));
            }
            for(int i = T; i != S; i = parent[i]) {
                long index = CIPHER * parent[i] + i;
                long rev = CIPHER * i + parent[i];
                flow.merge(index, minFlow, Integer::sum);
                flow.merge(rev, -minFlow, Integer::sum);
            }
            res += minFlow;
        }
        return res;
    }
}