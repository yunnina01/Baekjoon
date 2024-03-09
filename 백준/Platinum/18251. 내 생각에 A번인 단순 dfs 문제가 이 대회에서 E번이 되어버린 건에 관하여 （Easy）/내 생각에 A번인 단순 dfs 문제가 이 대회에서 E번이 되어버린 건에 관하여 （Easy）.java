import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Node[] nodes = new Node[N];
        int h = 0, top = (N + 1) >> 1, spacing = N + 1, size = 1, cnt = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int cost = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(top + spacing * cnt++, h, cost);
			if(cnt == size) {
				size *= 2;
				spacing >>= 1;
				top >>= 1;
				cnt = 0;
				h++;
			}
		}
		Arrays.sort(nodes);

        long res = Long.MIN_VALUE;
		for(int up = 0; up < h; up++) {
			for(int down = up + 1; down <= h; down++) {
				long now = Long.MIN_VALUE, before = 0;
				for(int i = 0; i < N; i++) {
					if(up > nodes[i].y || down <= nodes[i].y)
                        continue;
					now = Math.max(now, before = Math.max(nodes[i].cost, before + nodes[i].cost));
				}
				res = Math.max(res, now);
			}
		}
		bw.write(res + "\n");
        br.close();
		bw.flush();
        bw.close();
	}
}

class Node implements Comparable<Node> {
    int x, y, cost;

    Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(x, o.x);
    }
}