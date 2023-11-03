import sys
input = sys.stdin.readline

R, C = map(int, input().split())
ground = [list(map(int, input().split())) for _ in range(R)]

if R % 2:
    print(('R' * (C - 1) + 'D' + 'L' * (C - 1) + 'D') * (R // 2) + 'R' * (C - 1))
elif C % 2:
    print(('D' * (R - 1) + 'R' + 'U' * (R - 1) + 'R') * (C // 2) + 'D' * (R - 1))
elif R % 2 == 0 and C % 2 == 0:
    low = 1000
    pos = [-1, -1]
    for i in range(R):
        if i % 2 == 0:
            for j in range(1, C, 2):
                if ground[i][j] < low:
                    low = ground[i][j]
                    pos = [i, j]
        else:
            for j in range(0, C, 2):
                if ground[i][j] < low:
                    low = ground[i][j]
                    pos = [i, j]
                    
    res = ('D' * (R - 1) + 'R' + 'U' * (R - 1) + 'R') * (pos[1] // 2)
    x, y = 2 * (pos[1] // 2), 0
    xbound = 2 * (pos[1] // 2) + 1

    while x != xbound or y != R - 1:
        if x < xbound and [y, xbound] != pos:
            x += 1
            res += 'R'
        elif x == xbound and [y, xbound - 1] != pos:
            x -= 1
            res += 'L'
        if y != R - 1:
            y += 1
            res += 'D'
    res += ('R' + 'U' * (R - 1) + 'R' + 'D' * (R - 1)) * ((C - pos[1] - 1) // 2)
    print(res)