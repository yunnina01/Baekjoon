import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D;
	static int map[][];
	static int archer[];
	static List<Point> monster;
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		archer = new int[3];
		monster = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					monster.add(new Point(i, j));
			}
		}

		locateArcher(0, 0);
		bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
	}

	static void locateArcher(int idx, int start) {
		if(idx == 3) {
			List<Point> list = copy();
			attackMonster(list);
			return;
		}
		for(int i = start; i < M; i++) {
			archer[idx] = i;
			locateArcher(idx + 1, i + 1);
		}
	}

	static List<Point> copy() {
		List<Point> tmp = new ArrayList<>();
		for(int i = 0; i < monster.size(); i++) {
			Point now = monster.get(i);
			tmp.add(new Point(now.x, now.y));
		}
		return tmp;
	}

	static void attackMonster(List<Point> list) {
		int cnt = 0;
		while(true) {
			if(list.isEmpty())
				break;

			List<Point> targets = new ArrayList<>();
			for(int i = 0; i < 3; i++) {
				PriorityQueue<Monster> pq = new PriorityQueue<>();
				for(Point now : list) {
					int d = Math.abs(now.x - N) + Math.abs(now.y - archer[i]);
					if(d <= D)
						pq.offer(new Monster(now.x, now.y, d));
				}

				if(!pq.isEmpty()) {
					Monster target = pq.poll();
					boolean flag = false;
					for(Point now : targets) {
						if(target.x == now.x && target.y == now.y) {
							flag = true;
							break;
						}
					}
					if(!flag)
						targets.add(new Point(target.x, target.y));
				}
			}

			for(Point now : targets) {
				for(int i = list.size() - 1; i >= 0; i--) {
					if(now.x == list.get(i).x && now.y == list.get(i).y) {
						list.remove(i);
						cnt++;
						break;
					}
				}
			}

			for(int i = list.size() - 1; i >= 0; i--) {
				list.get(i).x += 1;
				if(list.get(i).x == N)
					list.remove(i);
			}
		}
		res = Math.max(res, cnt);
	}
}

class Monster implements Comparable<Monster> {
	int x, y, d;

    Monster(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

	@Override
	public int compareTo(Monster o) {
		if(this.d == o.d)
			return this.y - o.y;
		else
			return this.d - o.d;
	}
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}