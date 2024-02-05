import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] solution = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int low = 0, high = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int left = i + 1, right = N;
			int target = -solution[i];
			while(left < right) {
				int mid = (left + right) >> 1;
				if(solution[mid] == target) {
					System.out.println(-target + " " + target);
					return;
				}
				else if(solution[mid] < target)
					left = mid + 1;
				else
					right = mid;
			}
			if(i != left - 1 && min > Math.abs(solution[i] + solution[left - 1])) {
				min = Math.abs(solution[i] + solution[left - 1]);
				low = solution[i];
				high = solution[left - 1];
			}
			
			if(left == N)
                continue;
			if(i != left && min > Math.abs(solution[i] + solution[left])) {
				min = Math.abs(solution[i] + solution[left]);
				low = solution[i];
				high = solution[left];
			}
		}
		bw.write(low + " " + high + "\n");
        br.close();
        bw.flush();
        bw.close();
	}
}