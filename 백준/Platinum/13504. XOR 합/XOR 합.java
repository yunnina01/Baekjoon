import java.io.*;
import java.util.*;

public class Main {
	static final int BIT = 1 << 30;
	
	static class Node {
		Node[] child;
		
		Node() {
			child = new Node[2];
		}
		
		void add(int num, int bit) {
			if(bit == 0)
				return;

			int now = (num & bit) == 0 ? 0 : 1;
			if(child[now] == null)
				child[now] = new Node();
			child[now].add(num & (bit - 1), bit >> 1);
		}
		
		int sum(int num, int bit, int res) {
			if(bit == 0)
				return res;

			int now = (num & bit) == 0 ? 0 : 1;
			int other = now ^ 1;
			if(child[other] != null) {
				res = (res << 1) + 1;
				return child[other].sum(num & (bit - 1), bit >> 1, res);
			} else {
				res = (res << 1) + 0;
				return child[now].sum(num & (bit - 1), bit >> 1, res);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			Node trie = new Node();
			int prefix = 0;
			int max = 0;
			trie.add(prefix, BIT);

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				prefix ^= Integer.parseInt(st.nextToken());
				max = Math.max(max, trie.sum(prefix, BIT, 0));
				trie.add(prefix, BIT);
			}
			sb.append(max + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
}