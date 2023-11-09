import sys
input = sys.stdin.readline

board = []
for i in range(10):
    tmp = list(input())
    for j in range(10):
        if tmp[j] == 'O':
            tmp[j] = True
            continue
        tmp[j] = False
    board.append(tmp)
dx, dy = [-1, 1, 0, 0, 0], [0, 0, 0, -1, 1]
res = 101

for f in range(1 << 10):
    a = []
    for i in range(10):
        a.append(board[i][:])
    cnt = 0

    for i in range(10):
        if f & (1 << i):
            cnt += 1
            for j in range(5):
                nx = i + dx[j]
                ny = 0 + dy[j]
                if 0 <= nx <= 9 and 0 <= ny <= 9:
                    a[ny][nx] = not a[ny][nx]

    for i in range(1, 10):
        for j in range(10):
            if not a[i - 1][j]:
                continue
            for k in range(5):
                nx = j + dx[k]
                ny = i + dy[k]
                if 0 <= nx <= 9 and 0 <= ny <= 9:
                    a[ny][nx] = not a[ny][nx]
            cnt += 1

    flag = True
    for i in range(10):
        if a[9][i] == True:
            flag = False
    if flag:
        res = min(res, cnt)
print(res if res != 101 else -1)