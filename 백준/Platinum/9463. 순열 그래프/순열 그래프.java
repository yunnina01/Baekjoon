import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            size = 2;
            while(true) {
                if(size >= n) {
                    size <<= 1;
                    break;
                }
                size <<= 1;
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++)
                map.put(Integer.parseInt(st.nextToken()), i);

            tree = new int[size];
            long res = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                int idx = map.get(Integer.parseInt(st.nextToken()));
                res += getSum(1, 0, size / 2 - 1, idx + 1, n - 1);
                update(idx);
            }
            sb.append(res + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static int getSum(int node, int start, int end, int left, int right) {
        if(left > end || right < start)
            return 0;
        if(left <= start && right >= end)
            return tree[node];
        int mid = (start + end) >> 1;
        return getSum(node * 2, start, mid, left, right) + getSum(node * 2 + 1, mid + 1, end, left, right);
    }

    static void update(int idx) {
        idx += size / 2;
        tree[idx]++;
        while(idx > 1) {
            idx /= 2;
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }
    }
}