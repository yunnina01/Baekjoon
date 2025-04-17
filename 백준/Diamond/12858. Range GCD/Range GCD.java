import java.io.*;
import java.util.*;

public class Main {
    static long[] tree, lazy, diff, nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int size = 1 << ((int)Math.ceil(Math.log(N) / Math.log(2)) + 1);
        tree = new long[size];
        lazy = new long[size];
        diff = new long[size];
        nums = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        nums[1] = Long.parseLong(st.nextToken());
        for(int i = 2; i <= N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
            setDiff(1, 1, N, i, nums[i] - nums[i - 1]);
        }

        init(1, 1, N);

        int Q = Integer.parseInt(br.readLine());
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(T == 0)
                sb.append((Math.abs(A == B ? sum(1, 1, N, A, A) : gcd(sum(1, 1, N, A, A), getGcd(1, 1, N, A + 1, B)))) + "\n");
            else {
                update(1, 1, N, A, B, T);
                setDiff(1, 1, N, A, T);
                setDiff(1, 1, N, B + 1, -T);
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static long setDiff(int node, int start, int end, int idx, long val) {
        if(idx < start || idx > end)
            return diff[node];
        if(start == end)
            return diff[node] += val;
        int mid = (start + end) >> 1;
        return diff[node] = gcd(setDiff(node << 1, start, mid, idx, val), setDiff((node << 1) + 1, mid + 1, end, idx, val));
    }

    static long gcd(long a, long b) {
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }
    
    static long init(int node, int start,int end) {
        if(start == end)
            return tree[node] = nums[start];
        int mid = (start + end) >> 1;
        return tree[node] = init(node << 1, start, mid) + init((node << 1) + 1, mid + 1, end);
    }

    static long getGcd(int node, int start, int end, int left, int right) {
        if(left > right)
            return 0;
        if(left == start && right == end)
            return diff[node];
        int mid = (start+ end) >> 1;
        return gcd(getGcd(node << 1, start, mid, left, Math.min(right, mid)), getGcd((node << 1) + 1, mid + 1, end, Math.max(left, mid + 1), right));
    }

    static void update(int node, int start, int end, int left, int right, long val) {
        if(left > end || right < start)
            return;
        if(left <= start && right >= end) {
            lazy[node] += val;
            return;
        }
        int mid = (start + end) >> 1;
        tree[node] += (Math.min(end, right) - Math.max(start, left) + 1) * val;
        update(node << 1, start, mid, left, right, val);
        update((node << 1) + 1, mid + 1, end, left, right, val);
    }

    static long sum(int node, int start, int end, int left, int right) {
        if(left > end || right < start)
            return 0;
        tree[node] += lazy[node] * (end - start + 1);
        if(lazy[node] != 0 && end != start) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
        if(left <= start && end <= right)
            return tree[node];
        int mid = (start + end) >> 1;
        return sum(node << 1, start, mid, left, right) + sum((node << 1) + 1, mid + 1, end, left, right);
    }
}