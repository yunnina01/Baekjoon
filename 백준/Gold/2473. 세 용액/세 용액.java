import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		long[] solution = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();
		
		int[] res = {-1, -1, -1};
		long min = Long.MAX_VALUE;
		for(int i = 0; i < N - 2; i++) {
			for(int j = i + 1; j < N - 1; j++) {
				long target = -solution[i] - solution[j];
				int find = Arrays.binarySearch(solution, j + 1, N, target);
				if(find >= 0) {
					System.out.println(solution[i] + " " + solution[j] + " " + solution[find]);
					return;
				}

				find = -find - 1;
				if(find - 1 > j && Math.abs(solution[i] + solution[j] + solution[find - 1]) < min) {
					min = Math.abs(solution[i] + solution[j] + solution[find - 1]);
					res[0] = i;
					res[1] = j;
					res[2] = find - 1;
				}
				if(find != N && Math.abs(solution[i] + solution[j] + solution[find]) < min) {
					min = Math.abs(solution[i] + solution[j] + solution[find]);
					res[0] = i;
					res[1] = j;
					res[2] = find;
				}
			}
		}

		for(int i = 0; i < 3; i++)
			bw.write(solution[res[i]] + " ");
        bw.newLine();
        br.close();
        bw.flush();
        bw.close();
	}
}