class BOJ_30013
{
    static void Main(string[] args)
    {
        int N = int.Parse(Console.ReadLine());
        char[] pattern = Console.ReadLine().ToCharArray();

        int res = int.MaxValue;
        int term = 1;
        while (term <= N)
        {
            bool[] visit = Enumerable.Repeat(false, N).ToArray();
            int cnt = 0;
            for (int i = 0; i < N; i++)
            {
                if (pattern[i] == '#' && !visit[i])
                {
                    cnt++;
                    visit[i] = true;
                    for (int j = i; j < N; j += term)
                    {
                        if (pattern[j] == '.')
                            break;
                        visit[j] = true;
                    }
                }
            }
            term++;
            res = Math.Min(res, cnt);
        }
        Console.WriteLine(res);
    }
}