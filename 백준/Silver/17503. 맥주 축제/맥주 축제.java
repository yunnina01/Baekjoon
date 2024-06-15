import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Beer> beerList = new ArrayList<>();
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			beerList.add(new Beer(v, c));
		}
        
		beerList.sort(new Comparator<Beer>() {
			@Override
			public int compare(Beer o1, Beer o2) {
				if(o1.level == o2.level)
					return o2.prefer - o1.prefer;
				return o1.level - o2.level;
			}
		});

	    PriorityQueue<Integer> prefers = new PriorityQueue<>();
		int total = 0;
		int res = -1;
		for(Beer beer : beerList) {
			prefers.offer(beer.prefer);
			total += beer.prefer;
			if(prefers.size() > N)
				total -= prefers.poll();
			if(prefers.size() == N && total >= M) {
				res = beer.level;
				break;
			}
		}
        System.out.println(res);
	}
}

class Beer {
    int prefer, level;

    Beer(int prefer, int level) {
        this.prefer = prefer;
        this.level = level;
    }
}