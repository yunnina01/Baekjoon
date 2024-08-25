import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        int W = Integer.parseInt(st.nextToken());

        int[] height = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++)
            height[i] = Integer.parseInt(st.nextToken());
        
        int res = 0;
        for(int i = 1; i < W - 1; i++) {
            int left = 0, right = 0;
            for(int j = 0; j < i; j++)
                left = Math.max(left, height[j]);
            for(int j = i + 1; j < W; j++)
                right = Math.max(right, height[j]);
            
            if(height[i] < left && height[i] < right)
                res += Math.min(left, right) - height[i];
        }
        System.out.println(res);
    }
}