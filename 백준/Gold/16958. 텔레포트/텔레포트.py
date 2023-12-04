from collections import deque
import sys
input = lambda: sys.stdin.readline().rstrip()
read = lambda: map(int, input().split())

def calTaxiDist(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)

N, T = read()
info = []
special = set()
not_special = set()

for i in range(N):
    s, x, y = read()
    info.append((x, y))
    special.add(i) if s else not_special.add(i)

to_spec = [0] * N
for i in not_special:
    xi, yi = info[i]
    minDist = 2001
    for j in special:
        xj, yj = info[j]
        minDist = min(minDist, calTaxiDist(xi, yi, xj, yj))
    to_spec[i] = minDist

M = int(input())
for _ in range(M):
    A, B = read()
    res = min(calTaxiDist(*info[A - 1], *info[B - 1]), to_spec[A - 1] + T + to_spec[B - 1])
    print(f"{res}")