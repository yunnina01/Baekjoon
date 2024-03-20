import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		Student[] students = new Student[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
            int A = 0;
			for(int j = 0; j < M; j++)
				A |= (1 << Integer.parseInt(st.nextToken()));
			students[i] = new Student(d, A);
		}
		Arrays.sort(students);

		int[] know = new int[K + 1];
        int res = 0;
		for(int left = 0, right = 0; left <= right && right < N;) {
			if(students[right].tier - students[left].tier > D) {
				for(int i = 1; i <= K; i++)
					if(((1 << i) & students[left].know) != 0)
                        know[i]--;
				left++;
			} else {
				int score = 0;
				for(int i = 1; i <= K; i++) {
					if(((1 << i) & students[right].know) != 0)
                        know[i]++;
					if(know[i] > 0 && know[i] < right - left + 1)
                        score++;
				}
				res = Math.max(res, (right - left + 1) * score);
				right++;
			}
		}
		bw.write(res + "\n");
        br.close();
		bw.flush();
        bw.close();
	}
}

class Student implements Comparable<Student> {
    int tier, know;

    Student(int tier, int know) {
        this.tier = tier;
        this.know = know;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(tier, o.tier);
    }
}