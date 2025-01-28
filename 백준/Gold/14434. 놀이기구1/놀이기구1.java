import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> growingDays;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        
        int[] heightLimits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        growingDays = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            growingDays.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            growingDays.get(Integer.parseInt(st.nextToken())).add(i + 1);
        
        int[] psums = new int[K];
        for (int l = 0; l < Q; l++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int start = 1;
            int end = K + 1;
            while (start < end) {
                int mid = (start + end) >> 1;
                if (childHeight(i, mid) + childHeight(j, mid) < heightLimits[k - 1])
                    start = mid + 1;
                else
                    end = mid;
            }
            if (start <= K)
                psums[start - 1]++;
        }
        
        sb.append(psums[0] + "\n");
        for (int i = 1; i < psums.length; i++)
            sb.append((psums[i] += psums[i - 1]) + "\n");
        System.out.print(sb);
    }

    static int childHeight(int child, int day) {
        int start = 0;
        int end = growingDays.get(child).size() - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (growingDays.get(child).get(mid) <= day)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return end + 1;
    }
}