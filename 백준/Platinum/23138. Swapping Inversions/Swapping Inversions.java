import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[] sumTree;
    static int[] countTree;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        sumTree = new long[n + 1];
        countTree = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        Number[] numbers = new Number[n];
        for(int i = 0; i < n; i++)
            numbers[i] = new Number(i + 1, Long.parseLong(st.nextToken()));

        Arrays.sort(numbers);

        long res = 0;
        for(Number n : numbers) {
            res = (res + get(n.ind, n.x)) % MOD;
            update(n.ind, n.x);
        }
        System.out.println(res);
    }

    static void update(int x, long v) {
        while(x <= n) {
            sumTree[x] += v;
            countTree[x] += 1;
            x += (x & -x);
        }
    }

    static long get(int x, long v) {
        long sum = 0;
        int cnt = 0;
        while(x > 0) {
            sum += sumTree[x];
            cnt += countTree[x];
            x -= (x & -x);
        }

        long ret = (sum - cnt * v) % MOD;
        return ret;
    }
}

class Number implements Comparable<Number> {
    int ind;
    long x;

    Number(int ind, long x) {
        this.ind = ind;
        this.x = x;
    }

    @Override
    public int compareTo(Number o) {
        if(this.x != o.x)
            return o.x < this.x ? -1 : 1;
        return o.ind - this.ind;
    }
}