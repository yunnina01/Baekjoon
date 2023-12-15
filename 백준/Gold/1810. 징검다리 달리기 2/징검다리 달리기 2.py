import sys
from heapq import heappush as push
from heapq import heappop as hpop
input = sys.stdin.readline
read = lambda: map(int, input().split())
MAX = 1_000_001

def calDist(d1, d2):
    return ((d1[0] - d2[0]) ** 2 + (d1[1] - d2[1]) ** 2) ** 0.5

def dijkstra(v):
    cost[v] = 0
    push(heap, (0, v))
    while heap:
        cp, p = hpop(heap)
        if cost[p] < cp:
            continue
        if dots[p][1] == F:
            return int(cp + 0.5)
        for w, cw in graph[p]:
            ct = cp + cw
            if cost[w] > ct:
                cost[w] = ct
                push(heap, (ct, w))
    return -1


N, F = read()
graph = [[] for _ in range(N + 1)]
cost = [MAX * 5] * (N + 1)
dots = [(0, 0)]
heap = []
dic = [dict() for _ in range(MAX)]

dic[0][0] = 0
for i in range(1, N + 1):
    x, y = read()
    dots.append((x, y))
    dic[x][y] = i

for i in range(N + 1):
    x, y = dots[i]
    for dx in range(-2, 3, 1):
        for dy in range(-2, 3, 1):
            if not dx and not dy:
                continue
            nx = x + dx
            ny = y + dy
            if 0 <= nx < MAX and 0 <= ny <= F:
                if dic[nx].get(ny):
                    graph[i].append((dic[nx][ny], calDist((x, y), (nx, ny))))
print(dijkstra(0))