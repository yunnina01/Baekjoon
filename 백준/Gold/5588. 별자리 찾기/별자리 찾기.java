import java.io.*;
import java.util.*;

public class Main {
	static List<Move> moveList;
	static HashMap<Integer, HashSet<Integer>> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
		int m = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int prevX = Integer.parseInt(st.nextToken());
		int prevY = Integer.parseInt(st.nextToken());

        moveList = new ArrayList<>();
		int startX = prevX;
		int startY = prevY;
		for(int i = 1; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			moveList.add(new Move(y - prevY, x - prevX));
			prevX = x;
			prevY = y;
		}

		int n = Integer.parseInt(br.readLine());
        
        map = new HashMap<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if(!map.containsKey(x))
				map.put(x, new HashSet<>());
			map.get(x).add(y);
		}

		for(int x : map.keySet()) {
			for(int y : map.get(x)) {
				boolean flag = true;
				int nowX = x;
				int nowY = y;
				for(int i = 0; i < moveList.size(); i++) {
					int nextX = nowX + moveList.get(i).x;
					int nextY = nowY + moveList.get(i).y;
					if(map.containsKey(nextX)) {
						if(map.get(nextX).contains(nextY)) {
							nowX = nextX;
							nowY = nextY;
						} else {
							flag = false;
							break;
						}
					} else {
						flag = false;
						break;
					}
				}
				if(flag) {
					System.out.println((x - startX) + " " + (y - startY));
					return;
				}
			}
		}
	}
}

class Move {
	int x, y;

    Move(int y, int x) {
		this.y = y;
		this.x = x;
	}
}