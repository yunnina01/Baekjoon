import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] even = new int[N / 2];
        int[] odd = new int[N / 2];
        for(int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            if(i % 2 == 0)
                even[i / 2] = size - 1;
            else
                odd[i / 2] = size - 1;
        }
        Arrays.sort(even);
        Arrays.sort(odd);

        int minN = Integer.MAX_VALUE;
        int minC = -1;
        for(int i = 0; i < H; i++) {
            int now = N - (lower_bound(i, even) + lower_bound(H - i - 1, odd));
            if(minN > now) {
                minC = 1;
                minN = now;
            } else if(minN == now)
                minC ++;
        }
        System.out.println(minN + " " + minC);
    }

    static int lower_bound(int h, int[] arr) {
        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid] < h)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}