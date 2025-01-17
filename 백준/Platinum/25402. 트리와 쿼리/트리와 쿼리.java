import java.io.*;
import java.util.*;

public class Main {
	static Node[] adj;
	static int[] cnt, parent, treeParent, arr;
	static boolean[] visit;
	static int N;

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		adj	= new Node[N + 1];
		for(int i = 1; i < N; i++) {
			st	= new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			adj[A] = new Node(B, adj[A]);
			adj[B] = new Node(A, adj[B]);
		}

		cnt = new int[N + 1];
		parent = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			cnt[i] = 1;
			parent[i] = i;
		}
		
		treeParent = new int[N + 1];
		makeParent(1, -1);
		
		int Q = Integer.parseInt(br.readLine());
    
		arr = new int[N + 1];
		visit = new boolean[N + 1];
		while(Q-- > 0) {
			long res = 0;
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				visit[arr[i]]	= true;
			}

			for(int i = 0; i < K; i++) {
				if(visit[treeParent[arr[i]]]) {
					int ps = find(arr[i]);
					int tp = find(treeParent[arr[i]]);

					res += (long)cnt[ps] * cnt[tp];
					parent[ps] = tp;
					cnt[tp] += cnt[ps];
				}
			}
			
			for(int i = 0; i < K; i++) {
				visit[arr[i]] = false;
				cnt[arr[i]]	= 1;
				parent[arr[i]] = arr[i];
			}
			sb.append(res + "\n");
		}
		System.out.print(sb);
	}
	
	static void makeParent(int node, int prev) {
		for(Node now = adj[node]; now != null; now = now.next) {
			if(now.node != prev) {
				treeParent[now.node]= node; 
				makeParent(now.node, node);
			}
		}
	}

	static int find(int x) {
		return parent[x] = parent[x] == x ? x : find(parent[x]);
	}
}

class Node {
	int node;
	Node next;

	Node(int node, Node next) {
		this.node = node;
        this.next = next;
	}
}