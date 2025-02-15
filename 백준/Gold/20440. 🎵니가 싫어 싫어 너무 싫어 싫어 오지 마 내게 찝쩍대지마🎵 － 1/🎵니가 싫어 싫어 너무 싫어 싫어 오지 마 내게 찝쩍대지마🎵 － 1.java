import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

        HashSet<Integer> set = new HashSet<>();
        List<Query> queries = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int E = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());

			queries.add(new Query(E, X));
			set.add(E);
			set.add(X);
		}

		List<Integer> setList = new ArrayList<Integer>(set);
		Collections.sort(setList);
		Collections.sort(queries);
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> comp = new HashMap<Integer, Integer>();
		for(int i = 0; i < setList.size(); i++) {
			map.put(setList.get(i), i);
			comp.put(i, setList.get(i));
		}
        
		int[] arr = new int[setList.size() + 1];
		for(int i = 0; i < queries.size(); i++) {
			Query now = queries.get(i);
			int start = map.get(now.start) + 1;
			int end = map.get(now.end) + 1;

			arr[start]++;
			arr[end]--;
		}
        
		int[] prefix = new int[setList.size() + 1]; 
		prefix[0] = 0;
		for(int i = 1; i < setList.size() + 1; i++)
			prefix[i] = prefix[i - 1] + arr[i];

		int res = Integer.MIN_VALUE;
		for(int i = 0; i < prefix.length; i++)
			res = Math.max(res, prefix[i]);

        int start = -1;
        int last = -1;
		for(int i = 0; i < prefix.length; i++) {
			if(start == -1 && prefix[i] == res)
				start = i - 1;
			if(start != -1 && prefix[i] != res) {
				last = i - 1;
				break;
			}
		}
		if(last == -1)
			last = prefix.length - 1;
		System.out.println(res);
        System.out.println(comp.get(start) + " " + comp.get(last));
	}
}

class Query implements Comparable<Query> {
	int start, end;

	Query(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Query o) {
		return this.start - o.start;
	}
}