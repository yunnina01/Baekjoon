import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> startList;
	static Node[] forward, reverse;
	static int[] cycle_flag, cycle_depth;
	static boolean[] forward_visit, reverse_visit;
	static int N, K;

	public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		forward	= new Node[N];
		reverse = new Node[N];
        st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken()) - 1;
			forward[i] = new Node(x, forward[i]);
			reverse[x] = new Node(i, reverse[x]);
		}
		
		startList = new ArrayList<>();
		cycle_flag = new int[N];
		cycle_depth = new int[N];
		forward_visit = new boolean[N];
		reverse_visit = new boolean[N];
		for(int i = 0; i < N; i++) {
			if(!forward_visit[i]) {
				forward_visit[i] = true;
				cycle_flag[i] = i + 1;
				cycle_depth[i] = 0;
				findCycle(1, i, i + 1);
			}
		}
		
		int len = startList.size();
		int dp[][] = new int[len + 1][K + 1];
        int res = 0;
		for(int i = 1; i<=len; i++) {
			int min = startList.get(i - 1).get(0);
			int max = startList.get(i - 1).get(1);
			if(min > K)
                continue;
			
			for(int j = 0; j <= K; j++) {
				dp[i][j] = dp[i-1][j];
				for(int k = min; k <= max; k++) {
					if(j - k >= 0)
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k] + k);
				}
                if(j == K && dp[i][K] > res)
                    res = dp[i][K];
			}
		}
		System.out.println(res);
	}

	static boolean findCycle(int depth, int node, int flag) {
		for(Node now = forward[node]; now != null; now = now.next) {
			int nextNode = now.node;
			if(!forward_visit[nextNode]) {
				forward_visit[nextNode] = true;
				cycle_flag[nextNode] = flag;
				cycle_depth[nextNode] = depth;
				if(findCycle(depth + 1, nextNode, flag))
					return true;
			} else {
				int cycleCnt = depth - cycle_depth[nextNode];
				int compCnt = findReverse(nextNode);

				List<Integer> list = new ArrayList<>();
				list.add(cycleCnt);
				list.add(compCnt);
				startList.add(list);
				return true;
			}
		}
		return false;
	}
	
	static int findReverse(int node) {
		if(reverse_visit[node]) 
			return 0;

		forward_visit[node] = reverse_visit[node] = true;
		int cnt = 1;
		for(Node now = reverse[node]; now != null; now = now.next)
			cnt += findReverse(now.node);
		return cnt;
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