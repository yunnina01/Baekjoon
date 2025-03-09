import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
        int g = 0, res = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
            g ^= arr[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
			for(int j = 1; j <= arr[i]; j++) {
				if(((g ^ arr[i]) ^ (arr[i] - j)) == 0)
                    res++;
			}
		}
        System.out.println(res);
	}
}