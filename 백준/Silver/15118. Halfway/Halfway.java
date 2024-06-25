import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long aSum = getArithSum(1, n - 1) / 2;
        int left = 1, right = n - 1;
        while(left < right) {
            int mid = (left + right) >> 1;
            if(getArithSum(n - mid, n - 1) >= aSum)
                right = mid;
            else
                left = mid + 1;
        }
        System.out.println(left);
    }

    static long getArithSum(long a, long b) {
        return (b - a + 1) * (b + a);
    }
}