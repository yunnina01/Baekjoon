import java.io.*;
import java.util.*;

public class Main {
    static int[] grooves;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        grooves = new int[M];
        for(int i = 0; i < M; i++)
            grooves[i] = Integer.parseInt(br.readLine());
        
        int left = 0;
        int right = N;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(cut(grooves, mid))
                left = mid + 1;
            else
                right = mid - 1;
        }
        System.out.println(right == 0 ? -1 : right);
    }
    
    static boolean cut(int[] grooves, int length) {
        for(int i = 0; i < M; i++) {
            int cnt = 0;
            int prev = i;
            for(int j = i + 1; j < M; j++) {
                if(grooves[j] - grooves[prev] >= length) {
                    cnt++;
                    prev = j;
                }
            }
            for(int j = 0; j <= i; j++) {
                if((prev >= j ? N : 0) + grooves[j] - grooves[prev] >= length) {
                    cnt++;
                    prev = j;
                }
            }
            if(cnt >= K)
                return true;
        }
        return false;
    }
}