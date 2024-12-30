import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int start = 1;
		int end = N;
		int res = 0;
		while(start <= end) {
			int mid = (start + end) / 2;
			System.out.println("? " + mid);

			int ret = Integer.parseInt(br.readLine());

			if(mid - ret == ret) {
				res = mid;
				break;
			} else if(mid - ret < ret)
				start = mid + 1;
			else
				end = mid - 1;
		}
		System.out.println("! " + res);
	}
}