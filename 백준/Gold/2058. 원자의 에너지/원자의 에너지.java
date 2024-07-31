import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[][] dp;
	static int[] energies;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		energies = new int[N];
		for(int i = 0; i < N; i++)
			energies[i] = Integer.parseInt(br.readLine());

        HashSet<Integer> protons = new HashSet<>();
		for(int i = 0; i < M; i++)
			protons.add(Integer.parseInt(br.readLine()));

        adj = new ArrayList<>();
		for(int i = 0; i < N; i++)
			adj.add(new ArrayList<>());
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				int diff = Math.abs(energies[i] - energies[j]);
				if(protons.contains(diff)) {
					adj.get(i).add(j);
					adj.get(j).add(i);
				}
			}
		}

		dp = new int[N][2];
		for(int i = 0; i < N; i++) {
			dp[i][0] = -1;
			dp[i][1] = -1;
		}
		System.out.print(Math.max(DFS(0, 0, -1), DFS(0, 1, -1)));
	}
	
	static int DFS(int node, int isIn, int parent) {
		if(dp[node][isIn] != -1)
			return dp[node][isIn];
		if(isIn == 1) {
			dp[node][isIn] = energies[node];
			for(int next : adj.get(node)) {
				if(next == parent)
					continue;
				dp[node][isIn] += DFS(next, 0, node);
			}
			return dp[node][isIn];
		}

		dp[node][isIn] = 0;
		for(int next : adj.get(node)) {
			if(next == parent)
				continue;
			dp[node][isIn] += Math.max(DFS(next, 0, node), DFS(next, 1, node));
		}
		return dp[node][isIn];
	}
}