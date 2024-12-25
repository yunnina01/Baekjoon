import java.io.*;
import java.util.*;

public class Main {
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> angles = new ArrayList<>();
        check = new boolean[360];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int angle = Integer.parseInt(st.nextToken());

            angles.add(angle);
            checkAll(angle);

            int gcd = getGCD(angle, 360);
            angles.add(gcd);
            checkAll(gcd);
        }

        for(int i = 0; i < angles.size(); i++) {
            for(int j = i + 1; j < angles.size(); j++)
                checkAll(getGCD(angles.get(i), angles.get(j)));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            if(check[Integer.parseInt(st.nextToken())])
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }
        System.out.print(sb);
    }

    static void checkAll(int a) {
        if(check[a])
            return;
        int now = a;
        while(now < 360){
            check[now] = true;
            now += a;
        }
    }

    static int getGCD(int a, int b) {
        return b == 0 ? a : getGCD(b, a % b);
    }
}