import java.io.*;
import java.util.*;

public class Main {
    static int[] hi, arc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        hi = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            hi[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(hi);

        arc = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++)
            arc[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arc);

        long win = 0;
        long lose = 0;
        long draw = 0;
        for(int i = 0; i < N; i++) {
            int now = hi[i];
            int upper = upperBound(arc, now);
            int lower = lowerBound(arc, now);

            win += lower;
            draw += upper - lower;
            lose += M - upper;
        }
        System.out.println(win + " " + lose + " " +  draw);
    }

    static int upperBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;
        while(low < high) {
            int mid = (low + high) / 2;
            if(arr[mid] <= target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    static int lowerBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;
        while(low < high) {
            int mid = (low + high) / 2;
            if(arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}