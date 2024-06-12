import java.io.*;

public class Main {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] str = br.readLine().toCharArray();

        int[] res = new int[str.length];
        int cnt = 0;
        res[0] = 1;

        for(char c : str) {
            if(c == 'f') {
                cnt++;
                continue;
            }
            for(int i = 1; i <= cnt; i++)
                res[i] = ((res[i] % MOD) + (res[i - 1] % MOD)) % MOD;
        }
        System.out.println(res[cnt]);
    }
}