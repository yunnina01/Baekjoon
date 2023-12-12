import sys
from collections import defaultdict
from math import gcd
input = sys.stdin.readline

N = int(input())
slope = defaultdict(int)
balloons = [tuple(map(int, input().split())) for _ in range(N)]

for x, y in balloons:
    if x == 0 and y == 0:
        continue
    elif x == 0:
        if y < 0:
            slope[(0, -1)] += 1
        else:
            slope[(0, 1)] += 1
    elif y == 0:
        if x < 0:
            slope[(-1, 0)] += 1
        else:
            slope[(1, 0)] += 1
    else:
        tmp = gcd(x, y)
        x //= tmp
        y //= tmp
        slope[(x, y)] += 1

res = 0
for val in slope.values():
    res = max(res, val)
print(res)