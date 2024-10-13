import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());

        String res = "Kkeo-eok";
        long max = 0, now = 0;
        boolean chance = true;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            
            if(now <= x) {
                now += p;
                max = Math.max(max, p);
            } else if(!chance) {
                res = "Zzz";
                break;
            } else {
                chance = false;
                now -= now - max <= x ? Math.max(max - p, 0) : 0;
            }
        }
        System.out.println(res);
    }
}