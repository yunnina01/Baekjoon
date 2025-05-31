import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            if(a != 0)
                a--;
            long b = Long.parseLong(st.nextToken());
            sb.append(getSum(b) - getSum(a) + "\n");
        }
        System.out.print(sb);
    }

    static long getSum(long num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int numSum = 0;
        long sum = 0;
        for(int i = len; i > 0; i--) {
            int now = numStr.charAt(len - i) - '0';
            if(i == 1)
                now++;

            long nowSum = (long)(now * (i - 1) * Math.pow(10, i - 2) * 45);
            if(now != 0) {
                nowSum += Math.pow(10, i - 1) * (now - 1) * now / 2;
                nowSum += Math.pow(10, i - 1) * now * numSum;
            }
            numSum += now;
            sum += nowSum;
        }
        return sum;
    }
}