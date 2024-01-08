import java.io.*;
import java.util.*;

public class Main {
    static final int RANGE = 100001;
    static long[] tree, lazy;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<int[]> paradeList = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                paradeList.add(new int[]{x, y, 0, 1});
            }

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                paradeList.add(new int[]{l, b, t, 0});
                paradeList.add(new int[]{r, b, t, 2});
            }

            tree = new long[RANGE * 4];
            lazy = new long[RANGE * 4];
            paradeList.sort((o1, o2) -> {
                if(o1[0] != o2[0])
                    return o1[0] - o2[0];
                return o1[3] - o2[3];
            });

            long res = 0;
            for(int[] parade : paradeList) {
                if(parade[3] == 1) {
                    if(tree[1] == 0)
                        continue;
                    res += query(1, 0, RANGE - 1, parade[1]);
                } else {
                    if(parade[3] == 0)
                        update(1, 0, RANGE - 1, parade[1], parade[2], 1);
                    else
                        update(1, 0, RANGE - 1, parade[1], parade[2], -1);
                }
            }
            bw.write(res + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static void updateLazy(int node, int start, int end) {
        if(lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    static void update(int node, int start, int end, int left, int right, int val) {
        updateLazy(node, start, end);
        if(start > right || end < left)
            return;
        if (left <= start && end <= right) {
            tree[node] += (end - start + 1) * val;
            if(start != end) {
                lazy[node * 2] += val;
                lazy[node * 2 + 1] += val;
            }
            return;
        }
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, left, right, val);
        update(node * 2 + 1, mid + 1, end, left, right, val);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int idx) {
        updateLazy(node, start, end);
        if(idx < start || idx > end)
            return 0;
        if(start == end)
            return tree[node];
        int mid = (start + end) >> 1;
        return query(node * 2, start, mid, idx) + query(node * 2 + 1, mid + 1, end, idx);
    }
}