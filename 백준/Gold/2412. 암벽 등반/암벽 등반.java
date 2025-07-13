import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> points;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		points = new ArrayList<>();
		for(int i = 0; i <= T; i++)
			points.add(new ArrayList<>());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points.get(y).add(x);
		}
		for(int i = 0; i <= T; i++)
			Collections.sort(points.get(i));
		System.out.println(BFS(T));
	}
	
	static int BFS(int top) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0));
		int cnt = 0;
		while(!queue.isEmpty()) {
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				Point now = queue.poll();
				if(now.y == top)
                    return cnt;

				int miny = now.y - 2 >= 0 ? now.y - 2 : 0;
				int minx = now.x - 2 >= 0 ? now.x - 2 : 0;
				for(int j = miny; j <= (now.y + 2 > top ? top : now.y + 2); j++) {
					for(int k = 0; k < points.get(j).size(); k++) {
						if(points.get(j).get(k) < minx)
                            continue;
						if(points.get(j).get(k) > now.x + 2)
                            break;

						queue.offer(new Point(j, points.get(j).get(k)));
						points.get(j).remove(k);
						k--;
					}
				}
			}
			cnt++;
		}
		return -1;
	}
}

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}