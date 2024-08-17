import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		LinkedList<Node> mold = new LinkedList<>();
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			mold.offer(new Node(x, y, 0));
		}

		Node[] test = new Node[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			test[i] = new Node(x, y);
		}

	    boolean[][][] visit = new boolean[2][N][N];
		while(!mold.isEmpty()) {
			Node now = mold.poll();
			int nt = (now.t + 1) % 2;

			for(int[] dir : DIRECTIONS) {
				int nx = now.x + dir[0];
				int ny = now.y + dir[1];
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visit[nt][nx][ny] && now.t < t) {
                    visit[nt][nx][ny] = true;
                    mold.offer(new Node(nx, ny, now.t + 1));
                }
			}
		}

		boolean res = false;
		for(Node node : test) {
			if(visit[t % 2][node.x][node.y]) {
				res = true;
				break;
			}
		}
		System.out.println(res ? "YES" : "NO");
	}
}

class Node {
    int x, y, t;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Node(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}