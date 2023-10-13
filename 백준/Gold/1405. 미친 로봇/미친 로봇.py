import sys
input = sys.stdin.readline

N, e, w, s, n = map(int, input().split())
dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
percent = [e, w, s, n]
graph = [[0] * (2 * N + 1) for _ in range(2 * N + 1)]
res = 0

def DFS(x, y, p, cnt):
    global res
    if cnt == N:
        res += p
        return
    cur_p = p
    graph[x][y] = 1
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < (2 * N + 1) and 0 <= ny < (2 * N + 1) and graph[nx][ny] == 0:
            DFS(nx, ny, percent[i] / 100 * cur_p, cnt + 1)
            graph[nx][ny] = 0

DFS(N, N, 1, 0)
print(res)