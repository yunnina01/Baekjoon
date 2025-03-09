import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            long N = Long.parseLong(br.readLine());
            System.out.println(grundy(N) ? "cubelover" : "koosaga");
        }
    }

    static boolean grundy(long num) {
        return (((num % 2) ^ 1) | (num & 1501199875790164L)) != 0;
    }
}