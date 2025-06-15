import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

        List<Queue<Integer>> queueList = new ArrayList<>();
		for(int i = 0; i < 20; i++)
			queueList.add(new LinkedList<Integer>());

		long res = 0;
		for(int i = 0; i < N; i++) {
			int len = br.readLine().length() - 1;
			if(!queueList.get(len).isEmpty()) {
				while(!queueList.get(len).isEmpty() && queueList.get(len).peek() + K < i)
					queueList.get(len).poll();
				res += queueList.get(len).size();
			}
			queueList.get(len).add(i);
		}
		System.out.println(res);
	}
}