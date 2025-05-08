import java.io.*;

public class Main {
    static long[] arr;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long n = Long.parseLong(br.readLine());

        arr = new long[1000000];
		arr[1] = 1;
		for(int i = 1; i < 1000000; i++) {
			for(int j = 2 * i; j < 1000000; j += i)
				arr[j] -= arr[i];
		}

		long left = 0;
        long right = 100_000_000_000L;
		while(left < right - 1) {
			long mid = (left + right) >> 1;
            if(mid - solve(mid) < n)
                left = mid;
			else
                right = mid;
		}
		System.out.println(right);
	}

	static long solve(long num) {
		long ret = 0;
		for(long i = 1; i * i <= num; i++)
            ret += arr[(int)i] * num / (i * i);
		return ret;
	}
}