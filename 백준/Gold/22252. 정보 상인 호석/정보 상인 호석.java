import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int Q = Integer.parseInt(br.readLine());

	    HashMap<String, PriorityQueue<Integer>> map = new HashMap<>();
        long res = 0;
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				String name = st.nextToken();
				int k = Integer.parseInt(st.nextToken());

				if(!map.containsKey(name)) {
					PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
					for(int j = 0; j < k; j++)
						pq.add(Integer.parseInt(st.nextToken()));
					map.put(name, pq);
				} else {
					for(int j = 0; j < k; j++)
						map.get(name).add(Integer.parseInt(st.nextToken()));
				}
			} else {
				String name = st.nextToken();
				if(!map.containsKey(name))
                    continue;

				int b = Integer.parseInt(st.nextToken());
				for(int j = 0; j < b; j++) {
					if(map.get(name).isEmpty())
                        break;
					res += map.get(name).poll();
				}
			}
		}
		System.out.println(res);
	}
}