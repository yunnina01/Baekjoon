import sys
input = sys.stdin.readline

def DFS(x, y):
    if y == C - 1:
        return True
    for dx in [-1, 0, 1]:
        nx, ny = x + dx, y + 1
        if 0 <= nx < R and 0 <= ny < C and maps[nx][ny] != 'x' and not visit[nx][ny]:
            visit[nx][ny] = 1
            if DFS(nx, ny):
                return True
    return False

R, C = map(int, input().split())
maps = [list(input().rstrip()) for _ in range(R)]
visit = [[0] * C for _ in range(R)]
res = 0

for i in range(R):
    if DFS(i, 0):
        res += 1
print(res)