import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;
    static long[][] lazy;
    static long[] tree, A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        A = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        int startLeaf = 1;
        while(startLeaf < N)
            startLeaf <<= 1;
        int size = startLeaf * 2;
    
        lazy = new long[size + 1][2];
        tree = new long[size + 1];
        init(1, 1, N);

        for(int i = 0; i <= size; i++) {
            lazy[i][0] = 1;
            lazy[i][1] = 0;
        }

        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(op == 1) {
                long v = Long.parseLong(st.nextToken());
                update(1, 1, N, x, y, 1, v);
            } else if(op == 2) {
                long v = Long.parseLong(st.nextToken());
                update(1, 1, N, x, y, v, 0);
            } else if(op == 3) {
                long v = Long.parseLong(st.nextToken());
                update(1, 1, N, x, y, 0, v);
            } else
                sb.append(query(1, 1, N, x, y) + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static long init(int node, int start, int end) {
        if(start == end)
            return tree[node] = A[start];
        int mid = (start + end) >> 1;
        return tree[node] = (init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end)) % MOD;
    }

    static void propagation(int node, int start, int end) {
        long left = lazy[node][0];
        long right = lazy[node][1];
        if(left == 1 && right == 0)
            return;
        if(start != end) {
            for(int i : new int[] {node * 2, node * 2 + 1}) {
                lazy[i][0] = (lazy[i][0] * left) % MOD;
                lazy[i][1] = (lazy[i][1] * left + right) % MOD;
            }
        }
        tree[node] = tree[node] * left + (end - start + 1) * right;
        tree[node] %= MOD;

        lazy[node][0] = 1;
        lazy[node][1] = 0;
    }

    static void update(int node, int start, int end, int left, int right, long mul, long sum) {
        propagation(node, start, end);
        if(left > end || right < start)
            return;
        if(left <= start && right >= end) {
            lazy[node][0] = (lazy[node][0] * mul) % MOD;
            lazy[node][1] = (lazy[node][1] * mul) % MOD;
            lazy[node][1] = (lazy[node][1] + sum) % MOD;
            propagation(node, start, end);
            return;
        }

        int mid = (start + end) >> 1;
        update(node * 2, start, mid, left, right, mul, sum);
        update(node * 2 + 1, mid + 1, end, left, right, mul, sum);
        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
    }
    
    static long query(int node, int start, int end, int left, int right) {
        propagation(node, start, end);
        if(left > end || right < start)
            return 0;
        if(left <= start && right >= end)
            return tree[node] % MOD;
        int mid = (start + end) >> 1;
        return (query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right)) % MOD;
    }
}