from collections import deque
import sys
input = sys.stdin.readline

def solve():
    while dq:
        cnt, now, x, y = dq.popleft()
        nowBus = bus[now]

        if isHorizon[now]:
            if x == end[0] and nowBus[2] <= end[1] <= nowBus[4]:
                return cnt
        else:
            if y == end[1] and nowBus[1] <= end[0] <= nowBus[3]:
                return cnt

        for next, ny, nx1, nx2 in vertical:
            if visit[next]:
                continue
            if isHorizon[now]:
                if (nx1 <= x <= nx2) and (nowBus[2] <= ny <= nowBus[4]):
                    dq.append([cnt + 1, next, x, ny])
                    visit[next] = True
            else:
                if y == ny and ((nowBus[3] <= nx1) != (nowBus[1] <= nx2)):
                    dq.append([cnt + 1, next, nx1, y])
                    visit[next] = True

        for next, nx, ny1, ny2 in horizon:
            if visit[next]:
                continue

            if not isHorizon[now]:
                if (ny1 <= y <= ny2) and (nowBus[1] <= nx <= nowBus[3]):
                    dq.append([cnt + 1, next, nx, y])
                    visit[next] = True
            else:
                if x == nx and ((nowBus[4] <= ny1) != (nowBus[2] <= ny2)):
                    dq.append([cnt + 1, next, x, ny1])
                    visit[next] = True

m, n = map(int, input().split())
k = int(input())
visit = [False] * (k + 1)
horizon, vertical = [], []
bus = [[0]]
isHorizon = [False] * (k + 1)

for _ in range(k):
    b, x1, y1, x2, y2 = map(int, input().split())
    if x1 > x2:
        x2, x1 = x1, x2
    if y1 > y2:
        y2, y1 = y1, y2

    if x1 != x2:
        vertical.append([b, y1, x1, x2])
    else:
        horizon.append([b, x1, y1, y2])
        isHorizon[b] = True
    bus.append([b, x1, y1, x2, y2])

bus.sort(key = lambda x: x[0])
dest = list(map(int, input().split()))
start = dest[:2]
end = dest[2:]

dq = deque()
res = 0
for index, y, x1, x2 in vertical:
    if start[1] == y and x1 <= start[0] <= x2:
        dq.append([1, index, start[0], start[1]])
        visit[index] = True
for index, x, y1, y2 in horizon:
    if start[0] == x and y1 <= start[1] <= y2:
        dq.append([1, index, start[0], start[1]])
        visit[index] = True

print(solve())