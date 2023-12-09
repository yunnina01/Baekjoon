import sys
import heapq
input = sys.stdin.readline

N = int(input())
buildings, endpoints = [], []
for i in range(N):
    L, H, R = map(int, input().split())
    buildings.append((i, L, H, 0))
    buildings.append((i, R, H, 1))
    endpoints.append(R)
cur = 0
ended = set()
res, proceeding = [], []

buildings.sort(key = lambda x : (x[1], x[3], -x[2]))
for building in buildings:
    idx, x, height, flag = building
    if not flag:
        if height > cur:
            cur = height
            res.append((x, cur))
        heapq.heappush(proceeding, (-height, endpoints[idx]))
        continue

    ended.add(x)
    while proceeding and proceeding[0][1] in ended:
        heapq.heappop(proceeding)
    
    if proceeding:
        if cur != -proceeding[0][0]:
            cur = -proceeding[0][0]
            res.append((x, cur))
    else:
        if cur != 0:
            cur = 0
            res.append((x, cur))

for r in res:
    print(' '.join([str(r[0]), str(r[1])]), end = ' ')