import sys
input = sys.stdin.readline

N = int(input())
child = list(map(int, input().split()))
dp = [-1] * (N + 1)
for i, v in enumerate(child):
    dp[v] = i
longest, cnt = 0, 1

for i in range(1, N):
    if dp[i] < dp[i + 1]:
        cnt += 1
    else:
        longest = max(longest, cnt)
        cnt = 1
print(N - longest if N != 1 else 0)