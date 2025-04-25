import java.io.*;
import java.util.*;

public class Main {
	static final int MAX_CAPACITY = 101;
	static List<List<List<Edge>>> adj;
	static Edge[][] path;
    static int[][][] prev;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int m = Integer.parseInt(br.readLine());

            int[] hospital = new int[m];
            for(int j = 0; j < m; j++)
                hospital[j] = Integer.parseInt(br.readLine());
            
            int source = 0;
            int sink = n + 1;
            
            adj = new ArrayList<>();
            for(int j = 0; j <= sink; j++) {
                adj.add(new ArrayList<>());
                for(int k = 0; k <= s; k++)
                    adj.get(j).add(new ArrayList<>());
            }

            addEdge(source, i, 0, 0, g);
            for(int j = 1; j <= n; j++) {
                for(int k = 0; k < s; k++)
                    addEdge(j, j, k, k + 1, s);
            }

            int r = Integer.parseInt(br.readLine());

            for(int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                for(int k = 0; k <= s - t; k++)
                    addEdge(a, b, k, k + t, p);
            }

            for(int j = 0; j < m; j++) {
		        for(int k = 0; k <= s; k++)
			        addEdge(hospital[j], sink, k, 0, g);
            }

            prev = new int[sink + 1][s + 1][2];
            path = new Edge[sink + 1][s + 1];
            sb.append(maxFlow(new int[] {source, 0}, new int[] {sink, 0}, s) + "\n");
		}		
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void addEdge(int from, int to, int fromTime, int toTime, int capacity) {
		Edge forward = new Edge(to, toTime, capacity);
		Edge backward = new Edge(from, fromTime, 0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).get(fromTime).add(forward);
		adj.get(to).get(toTime).add(backward);
	}
	
	static int maxFlow(int[] source, int[] sink, int maxTime) {
		int totalFlow = 0;
		while(BFS(source, sink, maxTime)) {
			int flow = MAX_CAPACITY;
			for(int[] i = sink; !equal(i, source); i = prev[i[0]][i[1]])
				flow = Math.min(flow, path[i[0]][i[1]].capacity - path[i[0]][i[1]].flow);
			for(int[] i = sink; !equal(i, source); i = prev[i[0]][i[1]]) {
				path[i[0]][i[1]].flow += flow;
				path[i[0]][i[1]].reverse.flow -= flow;
			}
			totalFlow += flow;
		}
		return totalFlow;
	}
	
	static boolean BFS(int[] source, int[] sink, int maxTime) {
		Queue<int[]> queue = new LinkedList<>();
		int[] minus = new int[] {-1, -1};
		for(int i = 0; i < sink[0] + 1; i++) {
			for(int j = 0; j <= maxTime; j++)
				prev[i][j] = new int[] {-1, -1};
		}
		queue.offer(source);

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int node = now[0];
			int time = now[1];
			for(Edge line : adj.get(node).get(time)) {
				if(line.capacity - line.flow > 0 && equal(prev[line.to][line.time], minus)) {
					prev[line.to][line.time] = now;
					path[line.to][line.time] = line;
					if(line.to == sink[0])
						return true;
					queue.offer(new int[] {line.to, line.time});
				}
			}
		}
		return false;
	}
	
	static boolean equal(int[] a, int[] b) {
		return a[0] == b[0] && a[1] == b[1];
	}
}

class Edge {
    int to, time, capacity, flow;
    Edge reverse;
    
    Edge(int to, int time, int capacity) {
        this.to = to;
        this.time = time;
        this.capacity = capacity;
        this.flow = 0;
    }
}