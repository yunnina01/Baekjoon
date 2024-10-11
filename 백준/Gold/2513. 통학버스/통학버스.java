import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

	    PriorityQueue<Student> lq = new PriorityQueue<>();
        PriorityQueue<Student> rq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());

			if(pos < S)
                lq.offer(new Student(S - pos, cnt));
			else
                rq.offer(new Student(pos - S, cnt));
		}

        int res = 0;
		while(!rq.isEmpty()) {
			Student now = rq.poll();
			int cap = K;
			res += now.dis * 2;
			if(cap > now.num) {
				cap -= now.num;
				while(!rq.isEmpty()) {
					now = rq.poll();
					if(cap > now.num)
						cap -= now.num;
					else {
						if(cap != now.num) {
							now.num -= cap;
							rq.offer(now);
						}
						break;
					}
				}
			} else {
				if(cap != now.num) {
					now.num -= cap;
					rq.offer(now);
				}
			}
		}

		while(!lq.isEmpty()) {
			Student now = lq.poll();
			int cap = K;
			res += now.dis * 2;
			if(cap > now.num) {
				cap -= now.num;
				while(!lq.isEmpty()) {
					now = lq.poll();
					if(cap > now.num)
						cap -= now.num;
					else {
						if(cap != now.num) {
							now.num -= cap;
							lq.offer(now);
						}
						break;
					}
				}
			} else {
				if(cap != now.num) {
					now.num -= cap;
					lq.offer(now);
				}
			}
		}
		System.out.println(res);
	}
}

class Student implements Comparable<Student> {
	int dis, num;

	Student(int dis, int num) {
		this.dis = dis;
		this.num = num;
	}

	@Override
	public int compareTo(Student o) {
		return o.dis - this.dis;
	}
}