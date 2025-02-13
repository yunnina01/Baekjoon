import java.io.*;
import java.util.*;

public class Main {
	static List<List<Edge>> adj;
	static int gi, lastGi = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()) - 1;
        
		adj = new ArrayList<>();
		for(int i = 0; i < N; i++)
			adj.add(new ArrayList<>());

		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());

			adj.get(a).add(new Edge(b, d));
			adj.get(b).add(new Edge(a, d));
		}
		
		int giNode = getGi(-1, R);
		System.out.println(gi + " " + getGa(lastGi, giNode, 0));
	}
	
	static int getGi(int last, int now) {
		int len = adj.get(now).size();
		if(last == -1)
            len++;
		if(len == 1) { 
			lastGi = last;
			return now;
		}
		if(len > 2) { 
			lastGi = last;
			return now;
		}

		int r = -1;
		for(Edge next : adj.get(now)) {
			if(last == next.end)
                continue;
			gi += next.cost;
			r = getGi(now, next.end);
		}
		return r;
	}
	
	static int getGa(int last, int now, int sum) {
		int len = adj.get(now).size();
		if(last == -1)
            len++;
		if(len == 1)
            return sum;
		
		int max = sum;
		for(Edge next : adj.get(now)) {
			if(next.end == last)
                continue;
			max = Math.max(max, getGa(now, next.end, sum + next.cost));
		}
		return max;
	}
}

class Edge {
	int end, cost;

    Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
}