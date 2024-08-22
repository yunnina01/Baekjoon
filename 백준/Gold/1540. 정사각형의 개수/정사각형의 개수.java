import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int sqrtN = (int)Math.sqrt(N);
        int diff = N - sqrtN * sqrtN;
        int res = 0;
        for(int i = 1; i < sqrtN; i++)
            res += i * i;

        int adding = 0;
        for(int i = diff; i > 0; i--) {
            res += adding;
            adding = (adding + 1) % sqrtN;
        }
        System.out.println(res);
    }
}