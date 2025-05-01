import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> edgeList, nums;
	static int[] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());

			edgeList = new ArrayList<>();
			nums = new ArrayList<>();
			for(int i = 0; i <= M; i++) { 
				edgeList.add(new ArrayList<>());
				nums.add(new ArrayList<>());
			}

			depth = new int[M + 1];
			for(int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				edgeList.get(A).add(B);
				depth[B]++;
			}
			sb.append(K + " " + DAG(M) + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static int DAG(int M) {
		Queue<Edge> queue = new LinkedList<>();
		for(int i = 1; i <= M; i++) {
			if(depth[i] == 0) {  
				nums.get(i).add(1);
				queue.offer(new Edge(i, 1));
			}
		}

		for(int i = 0; i < M; i++) {
			if(queue.isEmpty())
                return -1;
            Edge edge = queue.poll();
            if(i == M - 1)
                return nums.get(edge.node).get(0);

            for(int now : edgeList.get(edge.node)) {
                if(nums.get(now).isEmpty())
                    nums.get(now).add(edge.num);
                else if(nums.get(now).get(0) < edge.num) { 
                    nums.get(now).clear();
                    nums.get(now).add(edge.num);
                } else if(nums.get(now).get(0) == edge.num)
                    nums.get(now).add(edge.num);

                if(--depth[now] == 0) {
                    if(nums.get(now).size() > 1) {
                        int nowNum = nums.get(now).get(0);
                        nums.get(now).clear();
                        nums.get(now).add(nowNum + 1);
                    }
                    queue.offer(new Edge(now, nums.get(now).get(0)));
                }
			}	
		}
		return -1;
	}
}

class Edge {
	int node, num;

    Edge(int node, int num) {
		this.node = node;
		this.num = num;
	}
}