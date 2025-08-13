import java.io.*;

public class Main {
    static final long MOD = 1_000_000_007;
    static final int MAX = 200_000;
    static long[] sumTree;
    static int[] countTree, minTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        countTree = new int[4 * (MAX + 1)];
        minTree = new int[4 * (MAX + 1)];
        sumTree = new long[4 * (MAX + 1)];

        int first = Integer.parseInt(br.readLine());
        updateCount(1, 0, MAX, first);
        updateSum(1, 0, MAX, first);

        long res = 1;
        for(int i = 1; i < N; i++) {
            int coord = Integer.parseInt(br.readLine());

            long temp = 0;
            temp += 1L * coord * queryCnt(1, 0, MAX, 0, coord - 1) - querySum(1, 0, MAX, 0, coord - 1);
            temp += querySum(1, 0, MAX, coord + 1, MAX) - 1L * coord * queryCnt(1, 0, MAX, coord + 1, MAX);

            updateCount(1, 0, MAX, coord);
            updateSum(1, 0, MAX, coord);
            temp %= MOD;
            res = (res * temp) % MOD;
        }
        System.out.println(res);
    }

    static int updateCount(int node, int start, int end, int val) {
        if(val < start || val > end)
            return countTree[node];
        if(start == end) {
            countTree[node] += 1;
            return countTree[node];
        }
        int mid = (start + end) / 2;
        return countTree[node] = updateCount(node * 2, start, mid, val) + updateCount(node * 2 + 1, mid + 1, end, val);
    }

    static long updateSum(int node, int start, int end, int val) {
        if(val < start || val > end)
            return sumTree[node];
        if(start == end) {
            sumTree[node] += start;
            return sumTree[node] ;
        }
        int mid = (start + end) / 2;
        return sumTree[node] = updateSum(node * 2, start, mid, val) + updateSum(node * 2 + 1, mid + 1, end, val);
    }

    static int queryCnt(int node, int start, int end, int left, int right) {
        if(left > end || right < start)
            return 0;
        if(left <= start && right >= end)
            return countTree[node];
        int mid = (start + end) / 2;
        return queryCnt(node * 2, start, mid, left, right) + queryCnt(node * 2 + 1, mid + 1, end, left, right);
    }


    static long querySum(int node, int start, int end, int left, int right) {
        if(left > end || right < start)
            return 0;
        if(left <= start && right >= end)
            return sumTree[node];
        int mid = (start + end) / 2;
        return querySum(node * 2, start, mid, left, right) + querySum(node * 2 + 1, mid + 1, end, left, right);
    }
}