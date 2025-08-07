import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 65537;
    static Queue<Integer> queue = new ArrayDeque<>();
    static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int size = 1;
        while(size <= INF)
            size <<= 1;
        tree = new int[size * 2];

        long res = 0;
        for(int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine()) + 1;
            queue.offer(n);
            update(1, 1, size, n, 1);

            if(i >= K) {
                res += query(1, 1, size, (K + 1) / 2) - 1;
                update(1, 1, size, queue.poll(), -1);
            }
        }
        System.out.println(res);
    }

    static void update(int node, int start, int end, int idx, int diff) {
        if(idx < start || idx > end)
            return;
        tree[node] += diff;
        if(start == end)
            return;
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx, diff);
        update(node * 2 + 1, mid + 1, end, idx, diff);
    }

    static int query(int node, int start, int end, int val) {
        if(start == end)
            return start;
        int mid = (start + end) >> 1;
        if(tree[node * 2] >= val)
            return query(node * 2, start, mid, val);
        return query(node * 2 + 1, mid + 1, end, val - tree[node * 2]);
    }
}