import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int numerator = a * d + b * c;
        int denominator = b * d;
        int gcd = GCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        bw.write(numerator + " " + denominator + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static int GCD(int a, int b) {
        if(b == 0)
            return a;
        return GCD(b, a % b);
    }
}