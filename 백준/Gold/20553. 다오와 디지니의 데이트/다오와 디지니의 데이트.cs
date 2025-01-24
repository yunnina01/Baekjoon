class BOJ_20553
{
    static void Main(string[] args)
    {
        StreamReader sr = new StreamReader(Console.OpenStandardInput());
        StreamWriter sw = new StreamWriter(Console.OpenStandardOutput());

        string[] input = sr.ReadLine().Split();
        int N = int.Parse(input[0]);
        int T = int.Parse(input[1]);
        int[] h = Array.ConvertAll(sr.ReadLine().Split(), int.Parse);

        long[] step = Enumerable.Repeat(0L, N).ToArray();
        long[] tp = Enumerable.Repeat(0L, N).ToArray();
        for (int i = 1; i < N; i++)
        {
            step[i] = h[i - 1] + h[i];
            tp[i] = tp[i - 1] + step[i];
        }

        long res = 0;
        T >>= 1;
        for (int i = 1; i < N; i++)
        {
            if (i <= T)
            {
                res = Math.Max(res, tp[i - 1] + step[i] * (T + 1 - i));
            }
        }

        sw.WriteLine(res);
        sw.Flush();
    }
}