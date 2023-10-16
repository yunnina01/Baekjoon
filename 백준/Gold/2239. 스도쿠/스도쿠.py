import sys
input = sys.stdin.readline

A = [list(map(int, input().rstrip())) for _ in range(9)]
row = [[0] * 10 for _ in range(9)]
col = [[0] * 10 for _ in range(9)]
rect = [[0] * 10 for _ in range(9)]
blank = []

def init():
    for i in range(9):
        for j in range(9):
            if not A[i][j]:
                blank.append((i, j))
            else:
                row[i][A[i][j]] = col[j][A[i][j]] = rect[i // 3 * 3 + j // 3][A[i][j]] = 1

def DFS(cnt):
    if cnt == len(blank):
        for i in A:
            print(''.join(list(map(str, i))))
        exit(0)
    else:
        x, y = blank[cnt][0], blank[cnt][1]
        for i in range(1, 10):
            if row[x][i] == col[y][i] == rect[x // 3 * 3 + y // 3][i] == 0:
                A[x][y] = i
                row[x][i] = col[y][i] = rect[x // 3 * 3 + y // 3][i] = 1
                DFS(cnt + 1)
                A[x][y] = row[x][i] = col[y][i] = rect[x // 3 * 3 + y // 3][i] = 0

init()
DFS(0)