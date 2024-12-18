import java.io.*;
import java.util.*;

public class Main {
    static Node[] tree;
    static int size = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        while(true) {
            if(size >= N) {
                size *= 2;
                break;
            }
            size *= 2;
        }

        tree = new Node[size];
        for(int i = 0; i < size / 2; i++) {
            if(i < N) {
                int a = Integer.parseInt(br.readLine());
                tree[i + size / 2] = new Node(a, a);
            } else
                tree[i + size / 2] = new Node(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        init();

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            int max = getMax(1, 0, size / 2 - 1, A, B);
            int min = getMin(1, 0, size / 2 - 1, A, B);
            sb.append((max - min) + "\n");
        }
        System.out.print(sb);
    }

    static void init() {
        for(int i = size / 2 - 1; i > 0; i--)
            tree[i] = new Node(Math.max(tree[i * 2].max, tree[i * 2 + 1].max), Math.min(tree[i * 2].min, tree[i * 2 + 1].min));
    }

    static int getMax(int node, int start, int end, int left, int right) {
        if(left > end || right < start)
            return Integer.MIN_VALUE;
        if(left <= start && right >= end)
            return tree[node].max;
        int mid = (start + end) / 2;
        return Math.max(getMax(node * 2, start, mid, left, right), getMax(node * 2 + 1, mid + 1, end, left, right));
    }

    static int getMin(int node, int start, int end, int left, int right) {
        if(left > end || right < start)
            return Integer.MAX_VALUE;
        if(left <= start && right >= end)
            return tree[node].min;
        int mid = (start + end) / 2;
        return Math.min(getMin(node * 2, start, mid, left, right), getMin(node * 2 + 1, mid + 1, end, left, right));
    }
}

class Node {
    int max, min;

    Node(int max, int min) {
        this.max = max;
        this.min = min;
    }
}