import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
        int[] P = new int[N];
		for(int i = 0; i < N; i++)
			P[i] = Integer.parseInt(br.readLine());
		
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(P[0], 1);
		long res = 1;
		for(int i = 1; i < N; i++) {
			int now = P[i];
			int lowHeight = -1;
			if(map.lowerKey(now) != null)
				lowHeight = map.lowerEntry(now).getValue() + 1;
            int highHeight = -1;
			if(map.higherKey(now) != null)
				highHeight = map.higherEntry(now).getValue() + 1;

			int height = lowHeight > highHeight ? lowHeight : highHeight;
			map.put(now, height);
			res += height;
		}
		System.out.println(res);
	}
}