import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int T = 0; T < 3; T++) {
            int N = Integer.parseInt(br.readLine());

            int[] coins = new int[100001];
            int total = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int price = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                for(int j = 1; j <= count; j++) {
                    for(int k = total; k > 0; k--) {
                        if(coins[k] > 0)
                            coins[k + (price * j)]++;
                    }
                }

                for(int j = 1; j <= count; j++)
                    coins[price * j]++;
                total += (price * count);
            }

            if(total % 2 != 0) {
                sb.append("0");
                if(T != 2)
                    sb.append("\n");
                continue;
            }

            if(coins[total / 2] != 0)
                sb.append("1");
            else
                sb.append("0");

            if(T != 2)
                sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}