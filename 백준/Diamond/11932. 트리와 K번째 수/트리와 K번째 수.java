import java.io.*;
import java.util.*;

public class Main {
    static Node[] trees;
	static Edge[] adj;
	static int[][] dp;
	static int[] arr, depth, unique, parents;
	static int N, logN;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		compression();

		adj = new Edge[N + 1];
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());

			adj[X] = new Edge(Y, adj[X]);
			adj[Y] = new Edge(X, adj[Y]);
		}

		logN = (int) Math.ceil(Math.log(N) / Math.log(2));
		dp = new int[logN + 1][N + 1];
		depth = new int[N + 1];
		parents = new int[N + 1];
		DFS(1, 0);

		for(int i = 1; i <= logN; i++) {
			for(int j = 1; j <= N; j++)
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
		}

		trees = new Node[N + 1];
		trees[0] = new Node();
		trees[0].init(0, N - 1);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
			sb.append(unique[query(X, Y, K)] + "\n");
		}
        bw.write(sb.toString());
        bw.flush();
	}
	
	static void compression() {
		HashMap<Integer, Integer> map = new HashMap<>();
        unique = new int[N];
		System.arraycopy(arr, 1, unique, 0, N);
		Arrays.sort(unique);
		
		for(int i = 0; i < N; i++)
			map.put(unique[i], i);
		for(int i = 1; i <= N; i++)
			arr[i] = map.get(arr[i]);
	}
	
	static void DFS(int now, int parent) {
		dp[0][now] = parent;
		depth[now] = depth[parent] + 1;
		parents[now] = parent;
		for(Edge child = adj[now]; child != null; child = child.next) {
			if(child.idx == parent)
				continue;
			DFS(child.idx, now);
		}
	}
	
	static int query(int x, int y, int k) {
		int lca = getLCA(x, y);
		return query(getTree(x), getTree(y), getTree(lca), getTree(parents[lca]), 0, N - 1, k);
	}
	
	static int query(Node x, Node y, Node lca, Node par, int start, int end, int k) {
		if(start == end)
			return start;

		int mid = (start + end) >> 1;
		int val = x.left.val + y.left.val - lca.left.val - par.left.val;
		if(val >= k)
			return query(x.left, y.left, lca.left, par.left, start, mid, k);
		return query(x.right, y.right, lca.right, par.right, mid + 1, end, k - val);
	}
	
	static int getLCA(int u, int v) {
		if(depth[u] < depth[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		for(int diff = depth[u] - depth[v], i = 0; diff != 0; diff >>= 1, i++) {
			if((diff & 1) == 1)
				u = dp[i][u];
		}
		if(u == v)
			return u;

		for(int i = logN; i >= 0; i--) {
			if(dp[i][u] != dp[i][v]) {
				u = dp[i][u];
				v = dp[i][v];
			}
		}
		return dp[0][u];
	}
	
	static Node getTree(int idx) {
		if(trees[idx] != null)
			return trees[idx];
		trees[idx] = getTree(parents[idx]).attach(0, N - 1, arr[idx]);
		return trees[idx];
	}
}

class Edge {
    int idx;
    Edge next;
    
    Edge(int idx, Edge next) {
        this.idx = idx;
        this.next = next;
    }
}
	
class Node {
    int val;
    Node left, right;
    
    void init(int start, int end) {
        if(start == end)
            return;

        int mid = (start + end) >> 1;
        left = new Node();
        right = new Node();
        left.init(start, mid);
        right.init(mid + 1, end);
    }
    
    Node attach(int start, int end, int num) {
        Node node = new Node();
        if(start != end) {
            int mid = (start + end) >> 1;
            if(num <= mid) {
                node.left = left.attach(start, mid, num);
                node.right = right;
            } else {
                node.left = left;
                node.right = right.attach(mid + 1, end, num);
            }
        }
        node.val = val + 1;
        return node;
    }
}