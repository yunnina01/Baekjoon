import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			visit = new boolean[N];
			sb.append(BFS(N) + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}

    static String BFS(int mod) {
        LinkedList<Node> queue = new LinkedList<>();
		queue.offer(new Node(1 % mod, "1"));
		visit[1 % mod] = true;

		String res = "BRAK";
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			if(now.r == 0) {
				res = now.s;
				break;
			}

			for(int i = 0; i < 2; i++) {
				int nr = (now.r * 10 + i) % mod;
				if(visit[nr])
					continue;
				visit[nr] = true;
				queue.add(new Node(nr, now.s + i));
			}
		}
		return res;
	}
}

class Node {
    int r;
    String s;

    Node(int r, String s) {
        this.r = r;
        this.s = s;
    }
}