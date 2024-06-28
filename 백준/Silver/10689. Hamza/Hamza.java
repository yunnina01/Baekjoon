import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			HashSet<Integer> set = new HashSet<>();
			int[] tju = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				tju[j] = Integer.parseInt(st.nextToken());
				set.add(tju[j]);
			}
			
			sb.append("Case " + i + ": ");
			for(int j = 0; j < N; j++) {
				if(!set.contains(tju[j]))
					continue;
				
				set.remove(tju[j]);
				if(set.isEmpty()) {
					sb.append((j + 1) + "\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}	
}