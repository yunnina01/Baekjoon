import sys
input = lambda : sys.stdin.readline().rstrip()

N = int(input())
coins = [0] * N
res = N ** 2

for i in range(N):
    for j, c in zip(range(N - 1, -1, -1), input()):
        if c == 'H':
            coins[i] += 1 << j
for bit in range(1 << N):
    cnt = 0
    for i in range(N):
        tmp = bin(coins[i] ^ bit).count('1')
        cnt += min(tmp, N - tmp)
    res = min(res, cnt)
print(res)