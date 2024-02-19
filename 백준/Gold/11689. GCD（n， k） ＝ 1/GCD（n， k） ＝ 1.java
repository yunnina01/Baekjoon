import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        long n = Long.parseLong(br.readLine());
        long tmp = n;
		for(long i = 2; i <= tmp; i++) {
			if(tmp % i == 0) {
				while(tmp % i == 0)
					tmp /= i;
				n = n / i * (i - 1);
			}
			if(i >= 1000000) {
				n = n / tmp * (tmp - 1);
				break;
			}
		}
		bw.write(n + "\n");
        br.close();
		bw.flush();
        bw.close();
	}
}