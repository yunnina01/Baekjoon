import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10_000_001;
    static List<Node> wormhole;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        while(TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

			wormhole = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()) - 1;
                int E = Integer.parseInt(st.nextToken()) - 1;
                int T = Integer.parseInt(st.nextToken());

				wormhole.add(new Node(S, E, T));
				wormhole.add(new Node(E, S, T));
            }
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken()) - 1;
				int E = Integer.parseInt(st.nextToken()) - 1;
				int T = Integer.parseInt(st.nextToken());

				wormhole.add(new Node(S, E, -T));
			}
            sb.append((bellmanFord(N, 0) ? "YES": "NO") + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean bellmanFord(int N, int index) {
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[index] = 0;
        boolean flag = false;

        for(int i = 0; i < N; i++) {
			for(Node now : wormhole) {
				if(dist[now.e] > dist[now.s] + now.v) {
					dist[now.e] = dist[now.s] + now.v;
					flag = true;
				}
			}
			if(!flag)
                return flag;
			flag = false;
		}

		for(Node now : wormhole) {
			if(dist[now.e] > dist[now.s] + now.v)
                return true;
		}
        return false;
    }
}

class Node {
    int s, e, v;

    Node(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }
}