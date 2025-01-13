import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] index = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            index[Integer.parseInt(st.nextToken())] = i;

        int[] order = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            order[i] = index[Integer.parseInt(st.nextToken())] + 1;

        SegmentTree left = new SegmentTree(N);
        SegmentTree right = new SegmentTree(N);
        long[] lsum = new long[N];
        long[] rsum = new long[N];
        for(int i = 0; i < N; i++) {
            lsum[i] = left.sum(1, 1, N, 1, order[i] - 1);
            rsum[N - 1 - i] = right.sum(1, 1, N, order[N - 1 - i] + 1, N);
            left.update(1, 1, N, order[i], 1);
            right.update(1, 1, N, order[N - 1 - i], 1);
        }

        long res = 0L;
        for(int i = 0; i < N; i++)
            res += lsum[i] * rsum[i];
        System.out.println(res == 0 ? "Attention is what I want" : ("My heart has gone to paradise\n" + res));
    }
}

class SegmentTree {
    long tree[];
    int treeSize;

    SegmentTree(int arrSize) {
        int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
        this.treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
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
        if(start > right || end < left)
            return 0;
        if(start >= left && end <= right)
            return tree[node];
        int mid = (start + end) >> 1;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }
}