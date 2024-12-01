import java.io.*;

public class Main {
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();

        String res = S.charAt(0) + "";
        for(int i = 1; i < S.length(); i++) {
            if(S.charAt(i) > res.charAt(i - 1))
                res = S.charAt(i) + res; 
            else
                res = res + S.charAt(i);
        }

        sb.append(res);
        System.out.println(sb.reverse().toString());
    }
}