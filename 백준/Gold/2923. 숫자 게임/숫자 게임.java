import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        int[] idxA = new int[101];
        int[] idxB = new int[101];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            idxA[A]++;
            idxB[B]++;
            int[] copyA = new int[101];
            int[] copyB = new int[101];
            System.arraycopy(idxA, 1, copyA, 1, 100);
            System.arraycopy(idxB, 1, copyB, 1, 100);

            int tmpA = 1;
            int tmpB = 100;
            int max = Integer.MIN_VALUE;
            int cnt = 0;
            while(true) {
                tmpA = findIdx(copyA, tmpA, true);
                tmpB = findIdx(copyB, tmpB, false);

                int diff = Math.min(copyA[tmpA], copyB[tmpB]);
                max = Math.max(max, tmpA + tmpB);
                copyA[tmpA] -= diff;
                copyB[tmpB] -= diff;
                cnt += diff;
                if(cnt == i)
                    break;
            }
            sb.append(max + "\n");
        }
        System.out.print(sb);
    }
    
    static int findIdx(int[] arr, int now, boolean plus) {
        while(arr[now] == 0) {
            if(plus)
                now++;
            else
                now--;
        }
        return now;
    }
}