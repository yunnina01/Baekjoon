import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
        final int INF = 1_000_000_000;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
        List<List<Node>> bus = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            bus.add(new ArrayList<>());
        
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            bus.get(u).add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] res = new int[N + 1];
        for(int i = 1; i <= N; i++)
            res[i] = INF;
        res[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(now.w != res[now.v])
                continue;
            for(Node next : bus.get(now.v)) {
                if(res[next.v] > res[now.v] + next.w) {
                    res[next.v] = res[now.v] + next.w;
                    pq.offer(new Node(next.v, res[next.v]));
                }
            }
        }

		bw.write(res[e] + "\n");
		br.close();
        bw.flush();
        bw.close();
	}
}

class Node implements Comparable<Node> {
    int v, w;

    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        if(this.w != o.w)
            return this.w - o.w;
        return this.v - o.v;
    }
}