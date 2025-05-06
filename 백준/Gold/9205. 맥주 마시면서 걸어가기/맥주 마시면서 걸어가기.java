import java.io.*;
import java.util.*;

public class Main {
	static Point start, end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
		int t = Integer.parseInt(br.readLine());

		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());

			List<Point> points = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			points.add(start);

			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			points.add(end);

			sb.append(BFS(points) + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static String BFS(List<Point> list) {
		Queue<Point> queue = new LinkedList<>();
		boolean[] visit = new boolean[list.size()];
		queue.offer(start);
		visit[0] = true;
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			if(now.equals(end))
                return "happy";

			for(int i = 0; i < list.size(); i++) {
				if(!visit[i] && getDist(now, list.get(i)) <= 1000) {
					visit[i] = true;
					queue.offer(list.get(i));
				}
			}
		}
		return "sad";
	}
	
	static int getDist(Point s, Point e) {
		return Math.abs(s.x - e.x) + Math.abs(s.y - e.y);
	}
}

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}