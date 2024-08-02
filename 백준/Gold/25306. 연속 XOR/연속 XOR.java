import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(getXOR(A - 1) ^ getXOR(B));
    }

    static long getXOR(long num) {
        long rem = num % 4;
        if(rem == 0)
            return num;
        else if(rem == 1)
            return 1;
        else if(rem == 2)
            return 1 ^ num;
        return 0;
    }
}