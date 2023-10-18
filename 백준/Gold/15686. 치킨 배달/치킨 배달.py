from itertools import combinations
import sys
input = sys.stdin.readline

N, M = map(int, input().rstrip().split())
city = [list(map(int, input().rstrip().split())) for _ in range(N)]
home, chicken = [], []
res = 1000000

for i in range(N):
    for j in range(N):
        if city[i][j] == 1:
            home.append((i, j))
        elif city[i][j] == 2:
            chicken.append((i, j))

for c in combinations(chicken, M):
    sum = 0
    for h in home:
        dis = 1000
        for i in range(M):
            dis = min(dis, abs(h[0] - c[i][0]) + abs(h[1] - c[i][1]))
        sum += dis
    res = min(res, sum)

print(res)