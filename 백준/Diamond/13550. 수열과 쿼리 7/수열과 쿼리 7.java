import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] A = new int[N + 1];
		A[0] = 0;
		for(int i = 1; i <= N; i++)
			A[i] = (A[i - 1] + Integer.parseInt(st.nextToken())) % K;

        int M = Integer.parseInt(br.readLine());

        int[][] query = new int[M][3];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			query[i][0] = i;
			query[i][1] = Integer.parseInt(st.nextToken()) - 1;
			query[i][2] = Integer.parseInt(st.nextToken());
		}

		int block = (int)Math.sqrt(N + 1);
		Arrays.sort(query, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int a = o1[1] / block;
				int b = o2[1] / block;
				return a == b ? o1[2] - o2[2] : a - b;
			}
		});

		ArrayList<Deque<Integer>> dq = new ArrayList<>();
		for(int i = 0; i <= K; i++)
			dq.add(new ArrayDeque<>());
		int[] bucket = new int[((N + 1) / block) + 1];
		int[] cnt = new int[N + 1];
        int[] res = new int[M];
		int start = query[0][1];
		int end = query[0][1] - 1;
		for(int i = 0; i < M; i++) {
			int left = query[i][1];
			int right = query[i][2];
			while(end < right) {
				if(dq.get(A[++end]).isEmpty()) {
					dq.get(A[end]).add(end);
					cnt[0]++;
					bucket[0]++;
				} else {
					int back = dq.get(A[end]).peekLast();
					int front = dq.get(A[end]).peek();
					cnt[back - front]--;
					bucket[(back - front) / block]--;
					dq.get(A[end]).add(end);
					cnt[end - front]++;
					bucket[(end - front) / block]++;
				}
			}
			while(left < start) {
				if(dq.get(A[--start]).isEmpty()) {
					dq.get(A[start]).addFirst(start);
					cnt[0]++;
					bucket[0]++;
				} else {
					int back = dq.get(A[start]).peekLast();
					int front = dq.get(A[start]).peek();
					cnt[back - front]--;
					bucket[(back - front) / block]--;
					dq.get(A[start]).addFirst(start);
					cnt[back - start]++;
					bucket[(back - start) / block]++;
				}
			}
			while(right < end) {
				int front = dq.get(A[end]).peek();
				cnt[end - front]--;
				bucket[(end - front) / block]--;
				dq.get(A[end]).removeLast();
				if(dq.get(A[end]).isEmpty())
					end--;
				else {
					int back = dq.get(A[end--]).peekLast();
					cnt[back - front]++;
					bucket[(back - front) / block]++;
				}
			}
			while(start < left) {
				int back = dq.get(A[start]).peekLast();
				cnt[back - start]--;
				bucket[(back - start) / block]--;
				dq.get(A[start]).remove();
				if(dq.get(A[start]).isEmpty())
					start++;
				else {
					int front = dq.get(A[start++]).peek();
					cnt[back - front]++;
					bucket[(back - front) / block]++;
				}
			}

            int j, k;
			for(j = bucket.length - 1; j >= 0; j--) {
				if(bucket[j] > 0)
					break;
			}
			for(k = Math.min(N, (j + 1) * block - 1); k >= j * block; k--) {
				if(cnt[k] > 0) {
					res[query[i][0]] = k;
					break;
				}
			}
		}
		for(int i = 0; i < M; i++)
			sb.append(res[i] + "\n");
		bw.write(sb.toString());
		bw.flush();
	}
}