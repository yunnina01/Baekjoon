import java.io.*;
import java.util.*;

public class Main {
	static HashSet<Integer> set;
	static int[] arr, ch;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];
		for(int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(br.readLine());

        set = new HashSet<>();
		ch = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			if(ch[i] == 0)
				DFS(i);
		}

		List<Integer> numList = new ArrayList<>(set);
		Collections.sort(numList);

		sb.append(numList.size() + "\n");
		for(int i = 0; i < numList.size(); i++)
			sb.append(numList.get(i) + "\n");
		System.out.print(sb);
	}
	
	static int DFS(int now) {
		if(ch[now] == 1) {
			ch[now] = 2;
			set.add(now);
			return now;
		}

		ch[now] = 1;
		if(ch[arr[now]] == 3) { 
			ch[now] = 3;
			return -1;
		}

		int ret = DFS(arr[now]);
		if(ret != -1) {
			set.add(now);
			ch[now] = 2;
			if(ret == now)
                ret = -1;
		} else
            ch[now] = 3;
		return ret;
	}
}