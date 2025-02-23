import java.io.*;
import java.util.*;

public class Main {
	static Node[] tree;
	static long[] A;
	static int len;
	
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		if(N == 2) {
            br.readLine();
            int M = Integer.parseInt(br.readLine());

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                char query = st.nextToken().charAt(0);
                if(query == '2')
                    sb.append("2\n");
            }
		} else {
            A = new long[N + 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++)
                A[i] = Integer.parseInt(st.nextToken());

            for(int i = 1; i < N; i++)
                A[i] = A[i + 1] - A[i];
            len = N - 2;
            for(int i = 1; i <= len; i++)
                A[i] = A[i + 1] - A[i];

            int size;
            for(size = 1; size < N; size <<= 1);
            tree = new Node[size << 1];
            init(1, 1, len);
            
            int M = Integer.parseInt(br.readLine());

            while(M-- > 0) {
                st = new StringTokenizer(br.readLine());
                char query = st.nextToken().charAt(0);
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

                if(query == '1') {
                    long x = Long.parseLong(st.nextToken());
                    long y = Long.parseLong(st.nextToken());

                    update(i - 2, x);
                    update(i - 1, -x + y);
                    update(j - 1, -x - (j - i + 1) * y);
                    update(j, x + (j - i) * y);
                } else if (j - i == 1)
                    sb.append("2\n");
                else
                    sb.append((query(1, 1, len, i, j - 2).max + 2) + "\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
	}
	
	static void init(int node, int start, int end) {
		if(start == end) {
			tree[node] = new Node(A[start] == 0);
			return;
		}
		int mid = (start + end) >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
		tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static void update(int idx, long num) {
		if(idx <= 0 || idx > len)
			return;
		if(num != 0) {
			if(A[idx] == 0)
				update(1, 1, len, idx, 0);
			else if (A[idx] + num == 0)
				update(1, 1, len, idx, 1);
			A[idx] += num;
		}
	}

	static void update(int node, int start, int end, int idx, int num) {
		if(start == end) {
			tree[node].fill(num);
			return;
		}
		int mid = (start + end) >> 1;
		if(idx <= mid)
			update(node << 1, start, mid, idx, num);
		else
			update(node << 1 | 1, mid + 1, end, idx, num);
		merge(tree[node << 1], tree[node << 1 | 1], tree[node]);
	}
	
	static void merge(Node left, Node right, Node dest) {
		dest.l = left.filled ? left.max + right.l : left.l;
		dest.r = right.filled ? right.max + left.r : right.r;
		dest.max = Math.max(Math.max(left.max, right.max), left.r + right.l);
		dest.filled = left.filled & right.filled;
	}
	
	static Node query(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return new Node();
		if(left <= start && right >= end)
			return tree[node];

		int mid = (start + end) >> 1;
		return merge(query(node << 1, start, mid, left, right), query(node << 1 | 1, mid + 1, end, left, right));
	}
	
	static Node merge(Node left, Node right) {
		Node result = new Node();
		result.l = left.filled ? left.max + right.l : left.l;
		result.r = right.filled ? right.max + left.r : right.r;
		result.max = Math.max(Math.max(left.max, right.max), left.r + right.l);
		result.filled = left.filled & right.filled;
		return result;
	}
}

class Node {
    int l, r, max;
    boolean filled;
    
    Node() {

    }
    
    Node(boolean flag) {
        if(flag)
            fill(1);
    }
    
    void fill(int num) {
        l = num;
        r = num;
        max = num;
        if(num == 0)
            filled = false;
        else
            filled = true;
    }
}