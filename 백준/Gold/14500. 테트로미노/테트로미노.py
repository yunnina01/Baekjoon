import sys
input = sys.stdin.readline

N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
visit = [[0] * M for _ in range(N)]
dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]
max_pos = max(map(max, board))
res = 0

def DFS(x, y, cnt, val):
    global res
    if res >= val + max_pos * (4 - cnt):
        return
    if cnt == 4:
        res = max(res, val)
    else:
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < M and not visit[nx][ny]:
                if cnt == 2:
                    visit[nx][ny] = 1
                    DFS(x, y, cnt + 1, val + board[nx][ny])
                    visit[nx][ny] = 0
                visit[nx][ny] = 1
                DFS(nx, ny, cnt + 1, val + board[nx][ny])
                visit[nx][ny] = 0

for i in range(N):
    for j in range(M):
        visit[i][j] = 1
        DFS(i, j, 1, board[i][j])
        visit[i][j] = 0
print(res)