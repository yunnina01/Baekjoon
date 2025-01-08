import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int res = 0;
        for(int i = 0; i < str.length(); i++)
            res = Math.max(res, KMP(str.substring(i)));
        System.out.println(res);
    }

    static int KMP(String p) {
        int[] SP = new int[p.length()];
        int max = 0;
        for(int i = 1, j = 0; i < p.length(); i++) {
            while(j > 0 && p.charAt(i) != p.charAt(j))
                j = SP[j - 1];
            if(p.charAt(i) == p.charAt(j)) {
                SP[i] = ++j;
                max = Math.max(max, SP[i]);
            }
        }
        return max;
    }
}