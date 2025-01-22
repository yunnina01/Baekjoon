using System.Text;

class QueueStack
{
    static void Main(string[] args)
    {
        StreamReader sr = new StreamReader(Console.OpenStandardInput());
        StreamWriter sw = new StreamWriter(Console.OpenStandardOutput());
        StringBuilder sb = new StringBuilder();

        int N = int.Parse(sr.ReadLine());
        int[] A = sr.ReadLine().Split().Select(int.Parse).ToArray();
        int[] B = sr.ReadLine().Split().Select(int.Parse).ToArray();
        int M = int.Parse(sr.ReadLine());
        int[] C = sr.ReadLine().Split().Select(int.Parse).ToArray();

        Queue<int> queue = new Queue<int>();
        for (int i = N - 1; i >= 0; i--)
        {
            if (A[i] == 0)
            {
                queue.Enqueue(B[i]);
            }
        }

        for (int i = 0; i < M; i++)
        {
            queue.Enqueue(C[i]);
            sb.Append(queue.Dequeue() + " ");
        }

        sw.WriteLine(sb.ToString());
        sw.Flush();
    }
}