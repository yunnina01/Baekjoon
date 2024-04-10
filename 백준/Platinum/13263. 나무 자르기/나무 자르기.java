import java.io.*;
import java.util.*;

public class Main { 
    static List<long[]> scope;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] B = new int[N];
        for(int i = 0; i < N; i++)
            B[i] = Integer.parseInt(st.nextToken());

        scope = new ArrayList<>();
        scope.add(new long[]{B[0], 0});

        long[] dp = new long[N];
        for(int i = 1; i < N; i++) {
            scope.add(new long[]{B[i], dp[i] = search(i)});
            while(scope.size() > 2 && calc(scope.size() - 3, scope.size() - 2) > calc(scope.size() - 2, scope.size() - 1))
                scope.remove(scope.size() - 2);
        }
        System.out.println(dp[N - 1]);
    }
    
    static long search(int idx) {
        int left = 0, right = scope.size() - 1;
        while(left < right) {
            int mid = (left + right) >> 1;
            if(calc(mid, mid + 1) <= A[idx])
                left = mid + 1;
            else
                right = mid;
        }
        return scope.get(left)[0] * A[idx] + scope.get(left)[1];
    }

    static long calc(int a, int b) {
        return (scope.get(a)[1] - scope.get(b)[1]) / (scope.get(b)[0] - scope.get(a)[0]);
    }
}