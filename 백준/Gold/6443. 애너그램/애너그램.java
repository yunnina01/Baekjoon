import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char[] str, now;
    static boolean[] ch;
    static int len;

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            String word = br.readLine();

            len = word.length();
            now = new char[len];
            ch = new boolean[len];
            str = word.toCharArray();
            Arrays.sort(str);

            backtracking(0);
        }
        System.out.print(sb);
    }

    static void backtracking(int L) {
        if(L == len) {
            for(int i = 0; i < len; i++)
                sb.append(now[i]);
            sb.append("\n");
            return;
        }

        char last = '0';
        for(int i = 0; i < len; i++) {
            if(str[i] == last || ch[i])
                continue;
            last = str[i];
            ch[i] = true;
            now[L] = str[i];
            backtracking(L + 1);
            ch[i] = false;
        }
    }
}