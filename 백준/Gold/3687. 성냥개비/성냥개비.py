import sys
input = sys.stdin.readline

INF = float('inf')
dp = [INF for _ in range(101)]
dp[2] = 1; dp[3] = 7; dp[4] = 4; dp[5] = 2; dp[6] = 6; dp[7] = 8; dp[8] = 10

for num in range(9, 101):
    for i in range(2, 8):
        if i != 6:
            dp[num] = min(dp[num], dp[num - i] * 10 + dp[i])
        else:
            dp[num] = min(dp[num], dp[num - i] * 10)

for _ in range(int(input())):
    n = int(input())
    res = ('7' if n % 2 else '1') + '1' * (n // 2 - 1)
    print(dp[n], res)