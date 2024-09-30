import java.io.*;
import java.util.*;

public class Main {
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int cnt = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            if(st.countTokens() == 1)
                break;
            sb.append(cnt + ". ");
            cnt++;

            list = new ArrayList<>();
            list.add(null);
            int res = Integer.MIN_VALUE;

            int N = Integer.parseInt(st.nextToken());

            for(int i = 0; i < N; i++) {
                int[] prefix = new int[i * 2 + 1 + 1];
                for(int j = 1; j <= i * 2 + 1; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    res = Math.max(res, num);
                    prefix[j] = prefix[j - 1] + num;
                }
                list.add(prefix);
            }

            res = Math.max(res, getTriangle(N));
            res = Math.max(res, getRTriangle(N));
            sb.append(res + "\n");
        }
        System.out.print(sb);
    }

    static int getTriangle(int N) {
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N - 1; i++) {
            for(int j = 1; j <= 2 * i - 1; j += 2) {
                int sum = 0;
                for(int k = 0; k <= N - i; k++) {
                    int[] prefix = list.get(i + k);
                    int tmp = getSum(j, j + 2 * k, prefix);
                    sum += tmp;
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }

    static int getSum(int start, int end, int[] prefix) {
        return prefix[end] - prefix[start - 1];
    }

    static int getRTriangle(int N) {
        int max = Integer.MIN_VALUE;
        for(int i = N; i >= 1; i--) {
            for(int j = 4; j <= 2 * i - 3; j += 2) {
                int sum = 0;
                for(int k = 0; k < Math.min(j / 2, (2 * i + 1 - j) / 2); k++) {
                    int[] prefix = list.get(i - k);
                    int tmp = getSum(j - 2 * k, j, prefix);
                    sum += tmp;
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }
}