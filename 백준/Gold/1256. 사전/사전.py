import sys
input = sys.stdin.readline

N, M, K = map(int, input().split())
dp = [[1] * (M + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
    for j in range(1, M + 1):
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

if dp[N][M] < K:
    print(-1)
else:
    res = ""
    while 1:
        if N == 0 or M == 0:
            res += "a" * N + "z" * M
            break
        if dp[N - 1][M] >= K:
            res += "a"
            N -= 1
        else:
            res += "z"
            K -= dp[N - 1][M]
            M -= 1
    print(res)