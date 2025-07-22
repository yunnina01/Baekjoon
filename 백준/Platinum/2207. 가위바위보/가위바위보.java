import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> list;
	static Stack<Integer> stack;
	static int[] sn, dfsn;
	static boolean[] visit;
	static int cnt, scc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for(int i = 0; i < M * 2; i++)
            list.add(new ArrayList<>());

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			A = (A < 0 ? -(A + 1) * 2 : A * 2 - 1);
			B = (B < 0 ? -(B + 1) * 2 : B * 2 - 1);
			list.get(oppo(A)).add(B);
			list.get(oppo(B)).add(A);
		}

        stack = new Stack<>();
		dfsn = new int[M * 2];
		sn = new int[M * 2];
		visit = new boolean[M * 2];
		cnt = 0;
		scc = 1;
		for(int i = 0; i < M * 2; i++) {
			if(dfsn[i] == 0)
                getSCC(i);
		}

		for(int i = 0; i < M; i++) {
			if(sn[i * 2] == sn[i * 2 + 1]) { 
				System.out.println("OTL");
				return;
			}
		}
		System.out.println("^_^");
	}
	
	static int oppo(int i) {
		if(i % 2 == 1)
            return i - 1;
		return i + 1;
	}
	
	static int getSCC(int i) {
		stack.push(i);
		dfsn[i] = ++cnt;
		
		int ret = dfsn[i];
		for(int next : list.get(i)) {
			if(dfsn[next] == 0)
                ret = Math.min(ret, getSCC(next));
			else if(!visit[next])
                ret = Math.min(ret, dfsn[next]);
		}
		
		if(ret == dfsn[i]) {
			while(true) {
				int top = stack.pop();
				sn[top] = scc;
				visit[top] = true;
				if(top == i)
                    break;
			}
			scc++;
		}
		return ret;
	}
}