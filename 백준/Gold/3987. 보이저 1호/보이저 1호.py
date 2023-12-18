import sys
input = lambda: sys.stdin.readline().rstrip()
read = lambda: map(int, input().split())

def dfs(d, r, c):
    cnt = 0
    x, y = r, c
    nx, ny = drc[d][0], drc[d][1]
    while 1:
        x += nx; y += ny; cnt += 1
        if x < 0 or x >= N or y < 0 or y >= M:
            return 0, cnt
        if grid[x][y] == 'C':
            return 0, cnt
        if x == r and y == c and nx == drc[d][0] and ny == drc[d][1]:
            return 1, cnt
        if grid[x][y] == '/':
            nx, ny = -ny, -nx
        if grid[x][y] == '\\':
            nx, ny = ny, nx

N, M = read()
grid = [input() for _ in range(N)]
PR, PC = read()
direction = ['U', 'R', 'D', 'L']
drc = ((-1, 0), (0, 1), (1, 0), (0, -1))
INF = int(12e12)
res = ['S', 0]
for i in range(4):
    a, t = dfs(i, PR - 1, PC - 1)
    if a and res[1] < t:
        res[0] = direction[i]
        res[1] = INF
    elif res[1] < t:
        res[0] = direction[i]
        res[1] = t
print(f'{res[0]}\n{res[1] if res[1] < INF else "Voyager"}')