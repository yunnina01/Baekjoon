import java.io.*;
import java.util.*;

public class Main {
	static final int MAX_X = 500000;
	static Node[] trees;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int M = Integer.parseInt(br.readLine());

        int max;
		for(max = 1; max <= MAX_X; max <<= 1);
		max--;
		trees = new Node[M + 1];
		trees[0] = new Node();
		trees[0].init(0, max);
		int idx = 0;
		
        int x, L, R, k;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken().charAt(0)) {
                case '1':
                    x = Integer.parseInt(st.nextToken());
                    trees[idx + 1] = trees[idx].add(0, max, x);
                    idx++;
                    break;
                case '2':
                    L = Integer.parseInt(st.nextToken()) - 1;
                    R = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    sb.append(xor(trees[L], trees[R], 0, max, (max + 1) >> 1, x) + "\n");
                    break;
                case '3':
                    k = Integer.parseInt(st.nextToken());
                    idx -= k;
                    break;
                case '4':
                    L = Integer.parseInt(st.nextToken()) - 1;
                    R = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    sb.append(small(trees[L], trees[R], 0, max, x) + "\n");
                    break;
                case '5':
                    L = Integer.parseInt(st.nextToken()) - 1;
                    R = Integer.parseInt(st.nextToken());
                    k = Integer.parseInt(st.nextToken());
                    sb.append(nth(trees[L], trees[R], 0, max, k) + "\n");
			}
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static final int xor(Node l, Node r, int start, int end, int bit, int num) {
		if(start == end)
			return start;

		int mid = (start + end) >> 1;
		if((r.left.val - l.left.val != 0 && (bit & num) != 0) || r.right.val - l.right.val == 0)
			return xor(l.left, r.left, start, mid, bit >> 1, num);
		return xor(l.right, r.right, mid + 1, end, bit >> 1, num);
	}
	
	static final int small(Node l, Node r, int start, int end, int num) {
		if(start > num)
			return 0;
		if(end <= num)
			return r.val - l.val;

		int mid = (start + end) >> 1;
		return small(l.left, r.left, start, mid, num) + small(l.right, r.right, mid + 1, end, num);
	}
	
	static final int nth(Node l, Node r, int start, int end, int num) {
		if(start == end)
			return start;

		int mid = (start + end) >> 1;
		int val = r.left.val - l.left.val;
		if(val >= num)
			return nth(l.left, r.left, start, mid, num);
		return nth(l.right, r.right, mid + 1, end, num - val);
	}
}
	
class Node {
    int val;
    Node left, right;
    
    void init(int start, int end) {
        if(start == end)
            return;

        int mid = (start + end) >> 1;
        left = new Node();
        right = new Node();
        left.init(start, mid);
        right.init(mid + 1, end);
    }
    
    Node add(int start, int end, int num) {
        Node node = new Node();
        if(start != end) {
            int mid = (start + end) >> 1;
            if(num <= mid) {
                node.left = left.add(start, mid, num);
                node.right = right;
            } else {
                node.left = left;
                node.right = right.add(mid + 1, end, num);
            }
        }
        node.val = val + 1;
        return node;
    }
}