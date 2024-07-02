import java.io.*;

public class Main {
    static int[] zrr;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();
        
        zrr = new int[1000000];
        len = S.length();
        Z(S);

        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            int i = Integer.parseInt(br.readLine());
            sb.append(zrr[len - i] + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void Z(String s) {
        StringBuilder rev = new StringBuilder(s);
        s = rev.reverse().toString();

        int left = 0, right = 0;
        zrr[0] = len;
        for(int i = 1; i < len; i++) {
            if(right < i) {
                zrr[i] = 0;
                while(i + zrr[i] < len && s.charAt(zrr[i]) == s.charAt(i + zrr[i]))
                    zrr[i]++;

                if(zrr[i] > 0) {
                    left = i;
                    right = i + zrr[i] - 1;
                }
            } else {
                if(i + zrr[i - left] - 1 < right)
                    zrr[i] = zrr[i - left];
                else {
                    zrr[i] = right - i + 1;
                    while(i + zrr[i] < len && s.charAt(zrr[i]) == s.charAt(i + zrr[i]))
                        zrr[i]++;

                    if(zrr[i] > 0) {
                        left = i;
                        right = i + zrr[i] - 1;
                    }
                }
            }
        }
    }
}