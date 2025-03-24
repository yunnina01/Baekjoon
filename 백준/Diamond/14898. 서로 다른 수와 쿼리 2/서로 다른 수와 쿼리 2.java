import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] prev = getPrev(arr, N);

		int end = N - 1;
		Node[] trees = new Node[N + 1];
		trees[0] = new Node();
		trees[0].init(0, end);
		for(int i = 0; i < N; i++)
			trees[i + 1] = trees[i].update(0, end, prev[i], i);

		int Q = Integer.parseInt(br.readLine());
        
        int res = 0;
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int l = res + Integer.parseInt(st.nextToken());
		    int r = Integer.parseInt(st.nextToken());

			res = trees[r].query(0, end, l - 1, r - 1);
			sb.append(res + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static int[] getPrev(int[] arr, int size) {
		int[] prev = new int[size];
		int[] unique = Arrays.stream(arr).distinct().sorted().toArray();
		int len = unique.length;
		int[] work = new int[len];
		for(int i = 0; i < len; i++)
			work[i] = -1;

		for(int i = 0; i < size; i++) {
			int idx = upperBound(unique, arr[i], len);
			prev[i] = work[idx];
			work[idx] = i;
		}
		return prev;
	}

	static int upperBound(int[] unique, int num, int right) {
		int left = 0;
		while(left < right) {
			int mid = (left + right) >> 1;
			if(unique[mid] < num)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
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
    
    Node delete(int start, int end, int idx) {
        Node node = new Node();
        if(start != end) {
            int mid = (start + end) >> 1;
            if(idx <= mid) {
                node.left = left.delete(start, mid, idx);
                node.right = right;
            } else {
                node.left = left;
                node.right = right.delete(mid + 1, end, idx);
            }
        }
        node.val = val - 1;
        return node;
    }
    
    Node add(int start, int end, int idx) {
        Node node = new Node();
        if(start != end) {
            int mid = (start + end) >> 1;
            if(idx <= mid) {
                node.left = left.add(start, mid, idx);
                node.right = right;
            } else {
                node.left = left;
                node.right = right.add(mid + 1, end, idx);
            }
        }
        node.val = val + 1;
        return node;
    }
    
    Node update(int start, int end, int delIdx, int addIdx) {
        if(delIdx == -1)
            return add(start, end, addIdx);

        Node node = new Node();
        if(start != end) {
            int mid = (start + end) >> 1;
            if(addIdx <= mid) {
                node.left = left.update(start, mid, delIdx, addIdx);
                node.right = right;
            } else if (delIdx <= mid) {
                node.left = left.delete(start, mid, delIdx);
                node.right = right.add(mid + 1, end, addIdx);
            } else {
                node.left = left;
                node.right = right.update(mid + 1, end, delIdx, addIdx);
            }
        }
        node.val = val;
        return node;
    }
    
    int query(int start, int end, int l, int r) {
        if(l > end || r < start)
            return 0;
        if(l <= start && r >= end)
            return val;

        int mid = (start + end) >> 1;
        return left.query(start, mid, l, r) + right.query(mid + 1, end, l, r);
    }
}