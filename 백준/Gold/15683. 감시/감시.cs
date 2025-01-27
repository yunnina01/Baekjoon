class BOJ_15683
{
    static (int x, int y)[] move = new (int x, int y)[] {(-1, 0), (0, 1), (1, 0), (0, -1)};
    static int[][][] direction = new int[6][][]
    {
        new int[][] {},
        new int[4][] {new int[]{0}, new int[]{1}, new int[]{2}, new int[]{3}},
        new int[2][] {new int[]{0 , 2}, new int[]{1, 3}},
        new int[4][] {new int[]{0 , 1}, new int[]{1 , 2}, new int[]{2 , 3}, new int[]{3 , 0}},
        new int[4][] {new int[]{3, 0, 1}, new int[]{0, 1, 2}, new int[]{1, 2, 3}, new int[]{2, 3, 0}},
        new int[1][] {new int[]{0, 1, 2, 3}}
    };
    static List<(int x, int y, int type)> cctv;
    static int N, M, res;

    static void Main(string[] args)
    {
        int[] input = Console.ReadLine().Split().Select(int.Parse).ToArray();
        N = input[0];
        M = input[1];

        cctv = new List<(int x, int y, int type)>();
        int[,] map = new int[N, M];
        for (int i = 0; i < N; i++)
        {
            input = Console.ReadLine().Split().Select(int.Parse).ToArray();
            for (int j = 0; j < M; j++)
            {
                if (input[j] > 0 && input[j] < 6)
                    cctv.Add((i, j, input[j]));
                map[i, j] = input[j];
            }
        }
        
        res = int.MaxValue;
        Search(map, 0);
        Console.Write(res);
    }

    static void Search(int[,] board, int k)
    {
        if (k == cctv.Count)
            res = Math.Min(res, Count(board));
        else
        {
            foreach (var dir in direction[cctv[k].type])
                Search(Watch((int[,])board.Clone(), cctv[k].x, cctv[k].y, dir), k + 1);		
        }
    }

    static int Count(int[,] board)
    {
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (board[i, j] == 0)
                    count++;

        return count;
    }

    static int[,] Watch(int[,] board, int x, int y, int[] direction)
    {
        foreach (var dir in direction)
        {
            int nx = x;
            int ny = y;
            while (true)
            {
                nx += move[dir].x;
                ny += move[dir].y;
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx, ny] == 6)
                    break;
                if (board[nx, ny] == 0)
                    board[nx, ny] = 1;
            }
        }
        return board;
    }
}