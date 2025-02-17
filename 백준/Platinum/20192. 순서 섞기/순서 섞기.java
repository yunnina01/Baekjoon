import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
				
		int N = Integer.parseInt(br.readLine());
		
		int temp = 0, bit = 1, cnt = 1;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int A = Integer.parseInt(st.nextToken());
			if(i > 0) {
				if((temp < A && bit == 0) || (temp > A && bit == 1)) {
					cnt++;
					bit ^= 1;
				}
			}
			temp = A;
		}
		
		for(int i = 0; i < N; i++) {
			if(cnt <= Math.pow(2, i)) {
                System.out.println(i);
                break;
			}
		}
	}
}