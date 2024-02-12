import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		
		Pos[] pos = new Pos[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			pos[i] = new Pos(i, x, y, z);
		}
		
        List<Node> tunnel = new ArrayList<>();
		Arrays.sort(pos, (o1, o2) -> o1.x - o2.x);
		for(int i = 0; i < N - 1; i++)
			tunnel.add(new Node(pos[i].idx, pos[i + 1].idx, Math.abs(pos[i].x - pos[i + 1].x)));
		Arrays.sort(pos, (o1, o2) -> o1.y - o2.y);
		for(int i = 0; i < N - 1; i++)
			tunnel.add(new Node(pos[i].idx, pos[i + 1].idx, Math.abs(pos[i].y - pos[i + 1].y)));
		Arrays.sort(pos, (o1, o2) -> o1.z - o2.z);
		for(int i = 0; i < N - 1; i++)
			tunnel.add(new Node(pos[i].idx, pos[i + 1].idx, Math.abs(pos[i].z - pos[i + 1].z)));
        tunnel.sort((o1, o2) -> o1.cost - o2.cost);
		
		parent = new int[N];
		for(int i = 0; i < N; i++)
			parent[i] = i;
		long res = 0;
		for(int i = 0; i < tunnel.size(); i++) {
			if(union(tunnel.get(i).start, tunnel.get(i).end)) {
				res += tunnel.get(i).cost;
				if(--N == 1)
					break;
			}
		}
		bw.write(res + "\n");
		br.close();
        bw.flush();
		bw.close();
	}
	
	static boolean union(int x, int y) {
		x = find(x);
        y = find(y);
        if(x == y)
            return false;
        parent[Math.max(x, y)] = Math.min(x, y);
        return true;
	}
	
	static int find(int x) {
		if(x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
    }
}

class Node {
	int start, end, cost;
	
	public Node(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
}

class Pos {
	int idx, x, y, z;
	
	public Pos(int idx, int x, int y, int z) {
		this.idx = idx;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}