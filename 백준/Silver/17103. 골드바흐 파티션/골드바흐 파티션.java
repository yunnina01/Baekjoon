import java.io.*;

public class Main {
    static boolean[] primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        eratos();
        
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int cnt = 0;

            for(int i = 2; i <= N / 2; i++) {
                if(!primes[i] && !primes[N - i])
                    cnt++;
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
    }

    static void eratos() {
        primes = new boolean[1000000];
        for(int i = 2; i < 1000000 / i; i++) {
            if(primes[i])
                continue;
            for(int j = i * i; j < 1000000; j += i)
                primes[j] = true;
        }
    }
}