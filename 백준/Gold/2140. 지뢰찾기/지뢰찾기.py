import sys
input = sys.stdin.readline

def DFS(bomb):
    global N, res
    while bomb:
        x, y = bomb.pop()
        for i in range(8):
            nx, ny = x + dx[i], y + dy[i]
            if nx == 0 or nx == N - 1 or ny == 0 or ny == N - 1:
                if board[nx][ny] == 0:
                    break
        else:
            for i in range(8):
                nx, ny = x + dx[i], y + dy[i]
                if nx == 0 or nx == N - 1 or ny == 0 or ny == N - 1:
                    board[nx][ny] -= 1
            res += 1

N = int(input())
board = [list(input()) for _ in range(N)]
bomb = []
dx = [0, 0, -1, 1, 1, -1, 1, -1]
dy = [1, -1, 0, 0, 1, -1, -1, 1]
res = 0

if N > 4:
    res += (N - 4) ** 2
for i in range(N):
    for j in range(N):
        if i == 1 or i == N - 2:
            if board[i][j] == '#':
                bomb.append((i, j))
            else:
                board[i][j] = int(board[i][j])
        elif 1 < i < N - 2:
            if j == 1 or j == N - 2:
                bomb.append((i, j))
            elif board[i][j] != '#':
                board[i][j] = int(board[i][j])
        elif board[i][j] != '#':
            board[i][j] = int(board[i][j])

DFS(bomb)
print(res)