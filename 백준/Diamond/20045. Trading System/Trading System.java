import java.io.*;
import java.util.*;

public class Main {
	static Node[] trees;
	static long[] prefix, unique;
	static int max;

	static class Node {
		int val;
		Node left, right;
		
		Node() {

		}
		
		Node(int val) {
			this.val = val;
		}
		
		Node(Node left, Node right) {
			this.left = left;
			this.right = right;
			this.val = left.val + right.val;
		}
		
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
			if(start == end)
				return new Node(val + 1);

			int mid = (start + end) >> 1;
			if(num <= mid) {
				Node child = left.add(start, mid, num);
				return new Node(child, right);
			} else {
				Node child = right.add(mid + 1, end, num);
				return new Node(left, child);
			}
		}
		
		int nth(int start, int end, int num) {
			if(start == end)
				return start;

			int mid = (start + end) >> 1;
			if(left.val >= num)
				return left.nth(start, mid, num);
			return right.nth(mid + 1, end, num - left.val);
		}
	}
	
	static class Result implements Comparable<Result> {
		int end, rank;
		long res;
		
		Result(int end) {
			this.end = end;
			this.rank = 1;
			calc();
		}
		
		Result calc() {
			res = prefix[end] - unique[trees[end - 1].nth(0, max, rank)];
			return this;
		}
		
		@Override
		public int compareTo(Result o) {
			return Long.compare(o.res, this.res);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		prefix = new long[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++)
			prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());

        PriorityQueue<Result> pq = new PriorityQueue<>();
		unique = Arrays.stream(prefix).distinct().sorted().toArray();
		max = unique.length - 1;

		trees = new Node[n + 1];
		trees[0] = new Node();
		trees[0].init(0, max);
		trees[0] = trees[0].add(0, max, upperBound(0L));
		for(int i = 1; i <= n; i++) {
			trees[i] = trees[i - 1].add(0, max, upperBound(prefix[i]));
			pq.add(new Result(i));
		}
		
		for(int i = 0; i < k; i++) {
			Result result = pq.poll();
			sb.append(result.res + " ");
			if(++result.rank <= result.end)
				pq.offer(result.calc());
		}
		bw.write(sb + "\n");
        bw.flush();
	}
	
	static int upperBound(long num) {
		int left = 0;
		int right = max;
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