import java.io.*;
import java.util.*;

public class Main {
    static final int MIN = -1_000_000_000;
    static int[][] dp;
    static int A, B, C;
    static String X, Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        X = br.readLine();
        Y = br.readLine();

        dp = new int[X.length()][Y.length()];
        for(int i = 0; i < X.length(); i++)
            Arrays.fill(dp[i], MIN);

        System.out.println(getMax(0, 0));
    }

    static int getMax(int xlen, int ylen) {
        if(xlen == X.length())
            return (Y.length() - ylen) * B;
        else if(ylen == Y.length())
            return (X.length() - xlen) * B;

        if(dp[xlen][ylen] != MIN)
            return dp[xlen][ylen];
        if(X.charAt(xlen) == Y.charAt(ylen))
            return A + getMax(xlen + 1, ylen + 1);

        int score = MIN;
        score = Math.max(score, B + getMax(xlen + 1, ylen));
        score = Math.max(score, B + getMax(xlen, ylen + 1));
        score = Math.max(score, C + getMax(xlen + 1, ylen + 1));
        return dp[xlen][ylen] = score;
    }
}