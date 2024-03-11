import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
		int N = Integer.parseInt(br.readLine());

		long[] A = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			A[i] = Long.parseLong(st.nextToken());

		SegmentTree segTree = new SegmentTree(N);
		segTree.init(A, 1, 1, N);

		Deque<int[]> updates = new ArrayDeque<>();
		List<long[]> sums = new ArrayList<>();
		int idx = 0;

        int M = Integer.parseInt(br.readLine());
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().charAt(0)=='1') {
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                updates.offerLast(new int[]{i, v});
            } else {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                sums.add(new long[]{idx++, 0, i, j, k});
            }
		}

		Collections.sort(sums, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return Long.compare(o1[2], o2[2]);
			}
		});

		idx = 0;
		for(long[] sum : sums) {
			for(; sum[2] > idx; idx++) {
				int[] update = updates.pollFirst();
				segTree.update(1, 1, N, update[0], update[1] - A[update[0]]);
				A[update[0]] = update[1];
			}
			sum[1] = segTree.sum(1, 1, N, (int)sum[3], (int)sum[4]);
		}

		Collections.sort(sums, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return Long.compare(o1[0], o2[0]);
			}
		});

		for(long[] sum : sums)
			sb.append(sum[1] + "\n");
		bw.write(sb.toString());
        br.close();
		bw.flush();
        bw.close();
	}

	static class SegmentTree {
		long tree[];
		int treeSize;
		
        SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];
		}
		
        long init(long[] arr, int node, int start, int end) {
			if(start == end)
				return tree[node] = arr[start];
            int mid = (start + end) >> 1;
			return tree[node] = init(arr, node * 2, start, mid) + init(arr, node * 2 + 1, mid + 1, end);
		}

        void update(int node, int start, int end, int idx, long diff) {
			if(idx < start || idx > end)
                return;
			tree[node] += diff;
			if(start != end) {
                int mid = (start + end) >> 1;
				update(node * 2, start, mid, idx, diff);
				update(node * 2 + 1, mid + 1, end, idx, diff);
			}
		}

		long sum(int node, int start, int end, int left, int right) {
			if(left > end || right < start)
				return 0;
			if(left <= start && end <= right)
				return tree[node];
            int mid = (start + end) >> 1;
			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
		}
	}
}