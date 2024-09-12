import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());

        int[] B = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            B[i] = Integer.parseInt(st.nextToken());

        int res = 0;
        while(true) {
            int sum = 0;
            for(int i = 0; i < N; i++)
                sum += B[i];
            if(sum == 0)
                break;

            boolean flag = false;
            for(int i = 0; i < N; i++) {
                if(B[i] == 0)
                    continue;
                if(B[i] % 2 == 1) {
                    B[i]--;
                    res++;
                    flag = true;
                }
            }
            if(!flag) {
                for(int i = 0; i < N; i++)
                    B[i] /= 2;
                res++;
            }
        }
        System.out.println(res);
    }
}