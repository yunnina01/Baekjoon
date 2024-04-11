import java.io.*;
import java.util.*;

public class Main {
    static int[][] tree;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int startLeaf = 1;
        while(startLeaf < N)
            startLeaf *= 2;
        int len = startLeaf * 2;

        tree = new int[len + 1][];
        init(1, 1, N);

        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            sb.append(query(1, 1, N, i, j, k) + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static void init(int node, int start, int end) {
        if(start == end)
            tree[node] = new int[]{arr[start]};
        else {
            int mid = (start + end) >> 1;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            merge(node, start, end);
        }
    }

    static void merge(int node, int start, int end) {
        tree[node] = new int[end - start + 1];
        int left = start;
        int mid = (start + end) >> 1;
        int right = mid + 1;
        int idx = 0;
        while(left <= mid && right <= end) {
            if(arr[left] < arr[right])
                tree[node][idx++] = arr[left++];
            else
                tree[node][idx++] = arr[right++];
        }

        while(left <= mid) 
            tree[node][idx++] = arr[left++];
        while(right <= end)
            tree[node][idx++] = arr[right++];

        for(int i = start; i <= end; i++)
            arr[i] = tree[node][i - start];
    }

    static int query(int node, int start, int end, int left, int right, int k) {
        if(start > right || end < left)
            return 0;
        if(left <= start && right >= end) {
            int upperIdx = upperBound(node, k);
            return tree[node].length - upperIdx;
        }
        int mid = (start + end) >> 1;
        return query(node * 2, start, mid, left, right, k) + query(node * 2 + 1, mid + 1, end, left, right, k);
    }

    static int upperBound(int node, int k) {
        int left = 0;
        int right = tree[node].length;
        while(left < right) {
            int mid = (left + right) >> 1;
            if(tree[node][mid] <= k)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }
}