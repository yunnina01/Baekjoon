import java.io.*;
import java.util.*;

public class Main {
    static int[] dp, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        dp = new int[(int) Math.pow(2, k + 1) - 1];
        tree = new int[(int) Math.pow(2, k + 1) - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < tree.length; i++)
            tree[i] = dp[i] = Integer.parseInt(st.nextToken());

        makePrefixSum(0);
        makeMax(0);
        System.out.println(Arrays.stream(tree).sum());
    }

    static int makePrefixSum(int i) {
        if(i >= dp.length)
            return 0;
        return dp[i] += Math.max(makePrefixSum(2 * i + 1), makePrefixSum(2 * i + 2));
    }

    static void makeMax(int i) {
        if(2 * i + 1 >= tree.length)
            return;
        int max = Math.max(dp[2 * i + 1], dp[2 * i + 2]);
        tree[2 * i + 1] += max - dp[2 * i + 1];
        tree[2 * i + 2] += max - dp[2 * i + 2];
        makeMax(2 * i + 1);
        makeMax(2 * i + 2);
    }
}