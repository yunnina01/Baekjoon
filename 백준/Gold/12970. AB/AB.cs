using System.Text;

class BOJ_12970
{
    static void Main(string[] args)
    {
        int[] inputs = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int N = inputs[0];
        int K = inputs[1];

        if ((N / 2) * (N - N / 2) < K)
        {
            Console.WriteLine("-1");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N / 2 + 1; i++)
        {
            int a = i;
            int b = N - i;
            if (a * b >= K) {
                sb.Append(new string('A', a - 1) + new string('B', b + 1));
                
                if (a - 1 != 0)
                    K -= (a - 1) * (b + 1) - (a - 1);
                else
                    K -= (a - 1) * (b + 1);

                for (int j = sb.Length - K - 1; j < sb.Length - K; j++)
                {
                    sb[j] = 'A';
                }
                Console.WriteLine(sb.ToString());
                return;
            }
        }
    }
}