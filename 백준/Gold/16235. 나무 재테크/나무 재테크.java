import java.io.*;
import java.util.*;

public class Main {
	static List<Tree> trees;	
	static Deque<Integer> deadTrees;
	static int[][] A, foods;
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		foods = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				foods[i][j] = 5;
			}
		}

		trees = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(row, col, age));
		}

		deadTrees = new LinkedList<>();
		while(K != 0) {
			spring();
			summer();
			fall();
			winter();
			K--;
		}
		System.out.println(trees.size());
	}

	static void spring() {
		for(int i = 0; i < trees.size(); i++) {
			Tree tree = trees.get(i);
			if(foods[tree.row][tree.col] < tree.age) {
				deadTrees.add(i);
				continue;
			}
			foods[tree.row][tree.col] -= tree.age;
			tree.age++;
		}
	}

	static void summer() {
		while(!deadTrees.isEmpty()) {
			int deadTreeIndex = deadTrees.pollLast();
			Tree deadTree = trees.get(deadTreeIndex);
			int food = deadTree.age / 2;
			foods[deadTree.row][deadTree.col] += food;
			deadTree.dead = true;
		}
	}

	static void fall() {
		int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};
		ArrayList<Tree> newTrees = new ArrayList<>();

		for(Tree tree : trees) {
			if(tree.dead)
				continue;
			if(tree.age % 5 == 0) {
				for(int i = 0; i < 8; i++) {
					int nr = tree.row + dr[i], nc = tree.col + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					newTrees.add(new Tree(nr, nc, 1));
				}
			}
		}

		for(Tree tree : trees) {
			if(!tree.dead)
				newTrees.add(tree);
		}
		trees = newTrees;
	}

	static void winter() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				foods[i][j] += A[i][j];
			}
		}
	}
}

class Tree implements Comparable<Tree> {
	int row, col, age;
	boolean dead;

	Tree(int row, int col, int age) {
		this.row = row;
		this.col = col;
		this.age = age;
	}

    @Override
    public int compareTo(Tree o) {
        return this.age - o.age;
    }
}