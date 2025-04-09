import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		Node root = new Node();
		int bit = 1 << 29;
		int max = 0;

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		add(root, A, bit);
		for(int i = 1; i < N; i++) {
			A = Integer.parseInt(st.nextToken());
			add(root, A, bit);
			max = Math.max(max, sum(root, A, bit, 0));
		}
		System.out.println(max);
	}

	static void add(Node node, int num, int bit) {
		if(bit == 0)
			return;
		
		if((num & bit) == 0) {
			if(node.zero == null)
				node.zero = new Node();
			add(node.zero, num & (bit - 1), bit >> 1);
		} else {
			if(node.one == null)
				node.one = new Node();
			add(node.one, num & (bit - 1), bit >> 1);
		}
	}

	static int sum(Node node, int num, int bit, int res) {
		if(bit == 0)
			return res;
		
		if((num & bit) == 0) {
			if(node.one != null) {
				res = (res << 1) + 1;
				return sum(node.one, num & (bit - 1), bit >> 1, res);
			} else {
				res = (res << 1) + 0;
				return sum(node.zero, num & (bit - 1), bit >> 1, res);
			}
		} else {
			if(node.zero != null) {
				res = (res << 1) + 1;
				return sum(node.zero, num & (bit - 1), bit >> 1, res);
			} else {
				res = (res << 1) + 0;
				return sum(node.one, num & (bit - 1), bit >> 1, res);
			}
		}
	}
}

class Node {
    Node zero, one;
    
    Node() {
        this.zero = null;
        this.one = null;
    }
}