import sys
from math import asin, pi
from heapq import heappush as push
from heapq import heappop as pop
input = sys.stdin.readline
MAX = 4000

def pythagoras(x1, y1, x2, y2):
    return (x1 - x2) ** 2 + (y1 - y2) ** 2

def cal(x1, y1, r1, x2, y2, r2):
    a = pythagoras(x1, y1, x2, y2)
    if a <= (r1 + r2) ** 2:
        return 0
    b = (r1 - r2) ** 2
    c = a - b
    r = 2.0 * c ** 0.5
    th = asin(c ** 0.5 / a ** 0.5)
    if r1 < r2:
        r1, r2 = r2, r1
    r += r1 * (pi - th) * 2.0 + r2 * th * 2.0
    return r

def prim(v):
    mst = [0] * N
    t, cnt = 0, 0
    push(heap, (0, v))
    while heap:
        cp, p = pop(heap)
        if mst[p]:
            continue
        mst[p] = 1
        t += cp
        cnt += 1
        if cnt == N:
            return t
        for w in range(N):
            if graph[p][w] > -1 and not mst[w]:
                push(heap, (graph[p][w], w))
    return t

N = int(input())
gears = [tuple(map(int, input().split())) for _ in range(N)]
graph = [[-1] * N for _ in range(N)]
heap = []
for i in range(N - 1):
    xi, yi, ri = gears[i]
    for j in range(i + 1, N):
        xj, yj, rj = gears[j]
        belt = cal(xi, yi, ri, xj, yj, rj)
        graph[i][j] = graph[j][i] = belt
print(prim(0))