import java.io.*;
import java.util.*;

public class Main {
	static final int MAXINF = 49_000_001;
	static final int MININF = -49_000_001;
	static List<List<Node>> adj;
	static int[] plutMoney, money;
	static int N, M, S, E;
	
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for(int i = 0; i < N; i++)   
        	adj.add(new ArrayList<>());
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	int price = -Integer.parseInt(st.nextToken());
        	adj.get(A).add(new Node(B, price));
        }
        
        plutMoney = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) 
        	plutMoney[i] = Integer.parseInt(st.nextToken());
        
        money = new int[N];
        Arrays.fill(money, MININF);
        String res = bellmanFord();
        if(res.length() != 1)
        	System.out.println(res);
        else
            System.out.println(money[E]);
    }
	
	static String bellmanFord() {
		String str = "1";
		money[S] = plutMoney[S];
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j < N; j++) {
				for(Node now : adj.get(j)) {
					int start = j;
					int end = now.node;
					int time = now.money;
					if(money[start] == MININF)
                        continue;
					
					int cost = money[start] + time + plutMoney[end];
					if(cost > MAXINF)
						cost = MAXINF;
					if(money[end] < cost)
						money[end] = cost;
				}
			}
		}

        if(money[E] == MININF)
			str = "gg";
		else {
			LOOP :
			for(int i = 0; i < N; i++) {
				for(Node now : adj.get(i)) {
					int start = i;
					int end = now.node;
					int time = now.money;
					if(money[start] == MININF)
                        continue;
					
					int cost = money[start] + time + plutMoney[end];
					if(money[end] < cost) {
						boolean first = BFS(S, end);
						boolean second = BFS(end, E);
						if(first && second) {
							str = "Gee";	
							break LOOP;
						}
					}
				}
			}
		}
		return str;
	}

	static boolean BFS(int start, int end) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visit = new boolean[N];
		dq.offer(start);
		boolean isPossible = false;

		LOOP :
		while(!dq.isEmpty()) {
			int now = dq.poll();
			if(!visit[now]) {
				visit[now] = true;
				for(int i = 0; i < adj.get(now).size(); i++) {
					int next = adj.get(now).get(i).node;
					if(next == end) {
						isPossible = true;
						break LOOP;
					}
					dq.offer(next);
				}
			}
		}
		return isPossible;
	}
}

class Node {
	int node, money;

	Node(int node, int money) {
		this.node = node;
		this.money = money;
	}
}