from collections import deque
import sys
input = sys.stdin.readline

dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
rect = [[0] * 2001 for _ in range(2001)]
visit = [[0] * 2001 for _ in range(2001)]
dq = deque()
start = []
res = 0

def BFS(x, y):
    dq.append([x, y])
    visit[x][y] = 1
    while dq:
        x, y = dq.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx <= 2000 and 0 <= ny <= 2000:
                if rect[nx][ny] == 1 and visit[nx][ny] == 0:
                    visit[nx][ny] = 1
                    dq.append([nx, ny])

for _ in range(int(input())):
    x1, y1, x2, y2 = map(int, input().split())
    x1 += 500; y1 += 500; x2 += 500; y2 += 500
    x1 *= 2; y1 *= 2; x2 *= 2; y2 *= 2
    start.append([x1, y1])
    for i in range(x1, x2 + 1):
        if i == x1 or i == x2:
            for j in range(y1, y2 + 1):
                rect[i][j] = 1
        else:
            rect[i][y1] = rect[i][y2] = 1

for i in range(len(start)):
    x, y = start[i]
    if visit[x][y] == 0:
        BFS(x, y)
        res += 1

if rect[1000][1000] == 1:
    res -= 1
print(res)