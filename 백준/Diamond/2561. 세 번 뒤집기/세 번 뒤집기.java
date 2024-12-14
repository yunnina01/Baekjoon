import java.io.*;
import java.util.*;

public class Main {
	static int[] res;
	static int N;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int[] board = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			board[i] = Integer.parseInt(st.nextToken());

		res = new int[6];
		flag = false;
		setReverseCase(board, 0);

		for(int i = 0; i < 3; i++)
			System.out.println((res[i * 2] + 1) + " " + (res[i * 2 + 1] + 1));
	}

	static void setReverseCase(int[] arr, int caseCnt) {
		if(flag)
			return;

		int[] indexes = getSectionsIndexes(arr);
		if(indexes[0] == -1 || (caseCnt == 1 && indexes[14] / 2 > 5) || (caseCnt == 2 && indexes[14] / 2 > 3))
			return;
		getSectionComb(arr, indexes, new int[2], indexes[14], 0, 0, caseCnt);
	}

	static int[] getSectionsIndexes(int[] arr) {
		int[] indexes = new int[15];
		int cnt = 0;
		int prev = arr[0];
		indexes[cnt++] = 0;

		for(int i = 1; i < N; i++) {
			int now = arr[i];
			if(Math.abs(now - prev) != 1) {
				indexes[cnt++] = i - 1;
				if(cnt >= 15)
					return new int[] {-1};

				indexes[cnt++] = i;
				if(cnt >= 15)
					return new int[] {-1};
			}
			prev = now;
		}
		indexes[cnt++] = N - 1;
		indexes[14] = cnt;
		return indexes;
	}

	static void getSectionComb(int[] arr, int[] indexes, int[] sel, int max, int cnt, int start, int caseCnt) {
		if (flag)
			return;
		if (cnt == 2) {
			setReverseCase(arr.clone(), indexes, sel, caseCnt);
			return;
		}
		for (int i = 0; i < max; i++) {
			sel[cnt] = i;
			getSectionComb(arr, indexes, sel, max, cnt + 1, i + 1, caseCnt);
		}
	}

	static void setReverseCase(int[] arr, int[] indexes, int[] sel, int caseCnt) {
		int[] copyArr = arr.clone();
		int start = indexes[sel[0]];
		int end = indexes[sel[1]];

		res[caseCnt * 2] = start;
		res[caseCnt * 2 + 1] = end;
		for(int i = start; i <= end; i++)
			arr[i] = copyArr[end - (i - start)];

		if(caseCnt == 2) {
			if(isboard(arr)) {
				flag = true;
				return;
			}
		} else
			setReverseCase(arr, caseCnt + 1);
	}

	static boolean isboard(int[] arr) {
		for(int i = 0; i < N; i++) {
			if(arr[i] != i + 1)
				return false;
		}
		return true;
	}
}