import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static final int INF = 8_972_000;
	static StringBuilder sb = new StringBuilder();
	static List<Edge> edges;
	static int[][] rip, time;
	static int W, H, G, E;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		while(true) {
			st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W == 0)
                break;

			G = Integer.parseInt(br.readLine());

			rip = new int[W][H];
			for(int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                rip[X][Y] = 1;
			}
			
			E = Integer.parseInt(br.readLine());

            edges = new ArrayList<>();
			for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
				int X1 = Integer.parseInt(st.nextToken());
                int Y1 = Integer.parseInt(st.nextToken());
				int X2 = Integer.parseInt(st.nextToken());
                int Y2 = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				edges.add(new Edge(new Node(X1, Y1), new Node(X2, Y2), T));
				rip[X1][Y1] = 2;
			}
			
			time = new int[W][H];
			for(int w = 0; w < W; w++) {
				for(int h = 0; h < H; h++) {
					time[w][h] = INF; 
					if((w == W - 1 && h == H - 1) || rip[w][h] != 0) 
						continue;
					
					for(int[] dir : DIRECTIONS) {
						int nw = w + dir[0];
						int nh = h + dir[1];
						if(nw < 0 || nw >= W || nh < 0 || nh >= H || rip[nw][nh] == 1) 
							continue; 
 
						edges.add(new Edge(new Node(w, h), new Node(nw, nh),1));
					}
				}
			}
			bellmanford();
		}
		System.out.print(sb);
	}

	static void bellmanford() {
		boolean cycle = false;
		time[0][0] = 0;
		int V = W * H - G;
		
		Loop : for(int i = 0; i < V; i++) {
			for(Edge now : edges) {
				Node start = now.start;
				Node end = now.end;
				if(time[start.w][start.h] == INF)
                    continue;
				
				int newTime = time[start.w][start.h] + now.time;
				if(time[end.w][end.h]> newTime) {
					time[end.w][end.h] = newTime;
					if(i == V - 1) {
						cycle = true;
						break Loop;
					}
				}
			}
		}
		
		if(cycle)
			sb.append("Never");
		else if(time[W - 1][H - 1] == INF)
			sb.append("Impossible");
		else
			sb.append(time[W - 1][H - 1]);
		sb.append('\n');
	}
}

class Edge {
	Node start, end;
	int time;

	Edge(Node start, Node end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
}

class Node {
	int w, h;

	Node(int w, int h) {
		this.w = w;
		this.h = h;
	}
}