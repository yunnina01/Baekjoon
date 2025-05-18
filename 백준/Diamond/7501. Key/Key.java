import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        for(long i = A; i <= B; i++) {
            if(BigInteger.valueOf(i).isProbablePrime(5) || i == 9)
                sb.append(i + " ");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}