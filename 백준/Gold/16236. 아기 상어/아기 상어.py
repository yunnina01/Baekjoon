import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]
dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]
cnt, size, res = 0, 2, 0
for i in range(N):
    for j in range(N):
        if graph[i][j] == 9:
            x, y = i, j
graph[x][y] = 0

def BFS(x, y, size):
    dist = [[0] * N for _ in range(N)]
    visit = [[0] * N for _ in range(N)]
    dq = deque()
    dq.append((x, y))
    visit[x][y] = 1
    tmp = []

    while dq:
        cur = dq.popleft()
        for i in range(4):
            nx, ny = cur[0] + dx[i], cur[1] + dy[i]
            if 0 <= nx < N and 0 <= ny < N and not visit[nx][ny]:
                if graph[nx][ny] <= size:
                    dq.append((nx, ny))
                    visit[nx][ny] = 1
                    dist[nx][ny] = dist[cur[0]][cur[1]] + 1
                    if graph[nx][ny] < size and graph[nx][ny]:
                        tmp.append((nx, ny, dist[nx][ny]))
    return sorted(tmp, key = lambda x : (-x[2], -x[0], -x[1]))

while 1:
    shark = BFS(x, y, size)
    if not len(shark):
        break
    nx, ny, dis = shark.pop()
    res += dis
    x, y = nx, ny
    graph[x][y] = 0
    cnt += 1
    if cnt == size:
        size += 1
        cnt = 0
print(res)