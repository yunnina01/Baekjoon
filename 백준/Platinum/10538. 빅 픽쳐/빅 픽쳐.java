import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int hp = Integer.parseInt(st.nextToken());
		int wp = Integer.parseInt(st.nextToken());

		int hm = Integer.parseInt(st.nextToken());
		int wm = Integer.parseInt(st.nextToken());
		
		long pow2W_1 = 1;
		for(int i = 0; i < wp - 1; i++)
			pow2W_1 *= 2;
		long pow3W_1 = 1;
		for(int i = 0; i < wp - 1; i++)
			pow3W_1 *= 3;
		long pow3H_1 = 1;
		for(int i = 0; i < hp - 1; i++)
			pow3H_1 *= 3;
		long pow5H_1 = 1;
		for(int i = 0; i < hp - 1; i++)
			pow5H_1 *= 5;

		boolean[][] dreamPic = new boolean[hp][wp];
		for(int i = 0; i < hp; i++) {
			String draw = br.readLine();
			for(int j = 0; j < wp; j++) {
				if(draw.charAt(j) == 'o')
					dreamPic[i][j] = true;
			}
		}

        long dreamHash1 = 0;
		long dreamHash2 = 0;
		for(int i = 0; i < hp; i++) {
			long tmp1 = 0;
			long tmp2 = 0;
			for(int j = 0; j < wp; j++) {
				if(dreamPic[i][j]) {
					tmp1 = tmp1 * 2 + 1;
					tmp2 = tmp2 * 3 + 1;
				} else {
					tmp1 = tmp1 * 2;
					tmp2 = tmp2 * 3;
				}
			}
			dreamHash1 = dreamHash1 * 3 + tmp1;
			dreamHash2 = dreamHash2 * 5 + tmp2;
		}

		boolean[][] teacherPic = new boolean[hm][wm];
		for(int i = 0; i<hm; i++) {
			String masterpiece = br.readLine();
			for(int j = 0; j < wm; j++) {
				if(masterpiece.charAt(j) == 'o')
					teacherPic[i][j] = true;
			}
		}

		long[][] teacherHash1 = new long[hm][wm - wp + 1];
		long[][] teacherHash2 = new long[hm][wm - wp + 1];
		for(int i = 0; i < hm; i++) {
			long tmp1 = 0;
			long tmp2 = 0;
			for(int j = 0; j < wp; j++) {
				if(teacherPic[i][j]) {
					tmp1 = tmp1 * 2 + 1;
					tmp2 = tmp2 * 3 + 1;
				} else {
					tmp1 = tmp1 * 2;
					tmp2 = tmp2 * 3;
				}
			}			
			teacherHash1[i][0] = tmp1;
			teacherHash2[i][0] = tmp2;

			for(int j = wp; j < wm; j++) {
				if(teacherPic[i][j - wp]) {
					tmp1 = (tmp1 - pow2W_1) * 2;
					tmp2 = (tmp2 - pow3W_1) * 3;
				} else {
					tmp1 = tmp1 * 2;
					tmp2 = tmp2 * 3;
				}

				if(teacherPic[i][j]) {
					tmp1++;
					tmp2++;
				}
				teacherHash1[i][j - wp + 1] = tmp1;
				teacherHash2[i][j - wp + 1] = tmp2;
			}
		}

		int res = 0;
		for(int j = 0; j < wm - wp + 1; j++) {
			long hash1 = 0;
			long hash2 = 0;
			for(int i = 0; i < hp; i++) {
				hash1 = hash1 * 3 + teacherHash1[i][j];
				hash2 = hash2 * 5 + teacherHash2[i][j];
			}

			if(dreamHash1 == hash1 && dreamHash2 == hash2)
				res++;
			
			for(int i = hp; i < hm; i++) {
				hash1 = (hash1 - pow3H_1 * teacherHash1[i - hp][j]) * 3 + teacherHash1[i][j];
				hash2 = (hash2 - pow5H_1 * teacherHash2[i - hp][j]) * 5 + teacherHash2[i][j];
				if(dreamHash1 == hash1 && dreamHash2 == hash2)
					res++;
			}
		}
		System.out.println(res);
	}
}