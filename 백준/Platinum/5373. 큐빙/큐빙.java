import java.io.*;
import java.util.*;

public class Main {
	static char[][][] cube = new char[5][5][5];;
	static char[][][] newCube = new char[5][5][5];
	static char[][] newTop = new char[5][5];
	static char[][] newSecond = new char[5][5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			for(int i = 1; i < 4; i++) {
				for(int j = 1; j < 4; j++) {
					cube[0][i][j] = 'w';
					cube[4][i][j] = 'y';

					cube[i][4][j] = 'r';
					cube[i][0][j] = 'o';

					cube[i][j][0] = 'g';
					cube[i][j][4] = 'b';
				}
			}
            
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				String op = st.nextToken();
				switch(op.charAt(0)) {
					case 'D':
						rollCube(0);
					case 'F':
						rollCube(0);
						break;
					case 'B':
						rollCube(1);
						break;
					case 'L':
						rollCube(2);
						break;
					case 'R':
						rollCube(3);
				}
				
				turnCube(op.charAt(1));
				
				switch(op.charAt(0)) {
					case 'D':
						rollCube(1);
					case 'F':
						rollCube(1);
						break;
					case 'B':
						rollCube(0);
						break;
					case 'L':
						rollCube(3);
						break;
					case 'R':
						rollCube(2);
				}
			}

			for(int i = 1; i < 4; i++) {
				for(int j = 1; j < 4; j++)
					sb.append(cube[0][i][j]);
				sb.append("\n");
			}
		}
        bw.write(sb.toString());
        bw.flush();
	}
	
	static void turnCube(char oper) {
		if (oper == '+') {
			for(int i = 1; i < 4; i++) {
				for(int j = 1; j < 4; j++)
					newTop[j][4 - i] = cube[0][i][j];
			}
			for(int i = 1; i < 4; i++) {
				for(int j = 1; j < 4; j++)
					cube[0][i][j] = newTop[i][j];
			}
			
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++)
					newSecond[j][4 - i] = cube[1][i][j];
			}
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++)
					cube[1][i][j] = newSecond[i][j];
			}
		} else {
			turnCube('+');
			turnCube('+');
			turnCube('+');
		}
	}
	
	static void rollCube(int oper) {
		if(oper == 0) {
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					for(int k = 0; k < 5; k++)
						newCube[4 - j][i][k] = cube[i][j][k];
				}
			}
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					for(int k = 0; k < 5; k++)
						cube[i][j][k] = newCube[i][j][k];
				}
			}
		} else if (oper == 1) {
			rollCube(0);
			rollCube(0);
			rollCube(0);
		} else if (oper == 2) {
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					for(int k = 0; k < 5; k++)
						newCube[k][j][4 - i] = cube[i][j][k];
				}
			}
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					for(int k = 0; k < 5; k++)
						cube[i][j][k] = newCube[i][j][k];
				}
			}
		} else {
			rollCube(2);
			rollCube(2);
			rollCube(2);
		}
	}
}