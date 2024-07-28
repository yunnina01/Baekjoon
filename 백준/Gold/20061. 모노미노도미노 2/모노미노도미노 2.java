import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		map = new int[11][11];
		for(int i = 0; i < 4; i++) {
			map[10][i] = 1;
			map[i][10] = 1;
		}

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

			move(t, x, y);

			for(int k = 9; k >= 5; k--) { 
				boolean flag = true;
				for(int j = 0; j < 4; j++) {
					if(map[k][j] == 0) {
						flag = false;
						break;
					}
				}
				if(flag) {
					delete(true, k);
					score++;
					k++;
				}
			}

            for(int k = 9; k >= 5; k--) {
				boolean flag = true;
				for(int j = 0; j < 4; j++) {
					if(map[j][k] == 0) {
						flag = false;
						break;
					}
				}
				if(flag) {
					delete(false, k);
					score++;
					k++;
				}
			}

			for(int k = 5; k >= 4; k--) {
				boolean flag = false;
				for(int j = 0; j < 4; j++) {
					if(map[k][j] == 1) {
						flag = true;
						break;
					}
				}
				if(flag) {
					delete(true, 9);
					k++;
				}
			}

			for(int k = 5; k >= 4; k--) {
				boolean flag = false;
				for(int j = 0; j < 4; j++) {
					if(map[j][k] == 1) {
						flag = true;
						break;
					}
				}
				if(flag) {
					delete(false, 9);
					k++;
				}
			}
		}

        int sum = 0;
		for(int i = 6; i < 10; i++) {
			for(int j = 0; j < 4; j++) {
				if(map[i][j] == 1)
                    sum++;
			}
		}
		for(int i = 6; i < 10; i++) {
			for(int j = 0; j < 4; j++) {
				if(map[j][i] == 1)
                    sum++;
			}
		}
		System.out.println(score + "\n" + sum);
	}

	static void move(int block, int y, int x) {
        int nx, ny;
		switch(block) {
            case 1:
                ny = y;
                while(ny < 10) {
                    if(map[ny + 1][x] == 1) {
                        map[ny][x] = 1;
                        break;
                    }
                    ny++;
                }
                break;
            case 2:
                ny = y;
                while(ny < 10) {
                    if(map[ny + 1][x + 1] == 1 || map[ny + 1][x] == 1) {
                        map[ny][x + 1] = 1;
                        map[ny][x] = 1;
                        break;
                    }
                    ny++;
                }
                break;
            case 3:
                ny = y + 1;
                while(ny < 10) {
                    if(map[ny + 1][x] == 1) {
                        map[ny][x] = 1;
                        map[ny - 1][x] = 1;
                        break;
                    }
                    ny++;
                }
		}

        switch(block) {
            case 1:
                nx = x;
                while(nx < 10) {
                    if(map[y][nx + 1] == 1) {
                        map[y][nx] = 1;
                        break;
                    }
                    nx++;
                }
                break;
            case 2:
                nx = x + 1;
                while(nx < 10) {
                    if(map[y][nx + 1] == 1) {
                        map[y][nx] = 1;
                        map[y][nx - 1] = 1;
                        break;
                    }
                    nx++;
                }
                break;
            case 3:
                nx = x;
                while(nx < 10) {
                    if(map[y][nx + 1] == 1 || map[y + 1][nx + 1] == 1) {
                        map[y][nx] = 1;
                        map[y + 1][nx] = 1;
                        break;
                    }
                    nx++;
                }
		}
	}
	
	static void delete(boolean dir, int line) {
		if(dir) {
			for(int i = line; i > 3; i--) {
				for(int j = 0; j < 4; j++)
					map[i][j] = map[i - 1][j];
			}
		} else {
			for(int i = line; i > 3; i--) {
				for(int j = 0; j < 4; j++)
					map[j][i] = map[j][i - 1];
			}
		}
	}
}