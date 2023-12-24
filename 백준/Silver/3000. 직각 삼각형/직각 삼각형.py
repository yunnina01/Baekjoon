import sys
input = sys.stdin.readline

N = int(input())
dots = [tuple(map(int, input().split())) for _ in range(N)]
xdic, ydic = {}, {}
res = 0

for x, y in dots:
    if x in xdic:
        xdic[x] += 1
    else:
        xdic[x] = 1

    if y in ydic:
        ydic[y] += 1
    else:
        ydic[y] = 1

for x, y in dots:
    res += (xdic[x] - 1) * (ydic[y] - 1)
print(res)