import java.io.*;

public class Main {
    static long[] tree;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] position = new int[N + 1];
		for(int i = 1; i <= N; i++)
			position[Integer.parseInt(br.readLine())] = i;

        tree = new long[N * 4];
		for(int i = 1; i <= N; i++) {
			if(i % 2 == 1) {
				int pos = position[1 + (i >>> 1)];
				sb.append(pos - 1 - sum(1, 1, N, 1, pos - 1) + "\n");
				update(1, 1, N, pos, 1);
			} else{
				int pos = position[N + 1 - (i >>> 1)];
				sb.append(N - pos - sum(1,1,N,pos+1,N) + "\n");
				update(1, 1, N, pos, 1);
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}

    static void update(int node, int start, int end, int idx, long diff) {
        if(idx < start || idx > end)
            return;
        tree[node] += diff;
        if(start != end) {
            int mid = (start + end) >> 1;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }

    static long sum(int node, int start, int end, int left, int right) {
        if(start > right || end < left)
            return 0;
        if(start >= left && end <= right)
            return tree[node];
        int mid = (start + end) >> 1;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }
}