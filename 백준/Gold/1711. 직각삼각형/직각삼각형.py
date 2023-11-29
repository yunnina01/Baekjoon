import sys
from math import gcd
input = sys.stdin.readline

N = int(input())
points = [[*map(int, input().split())] for _ in range(N)]
res = 0

for i in points:
    dic = {}
    for j in points:
        if i == j:
            continue
        else:
            X, Y = j[0] - i[0], j[1] - i[1]
            GCD = gcd(abs(X), abs(Y))
            if X * Y < 0:
                tmp = (-(abs(X) // GCD), abs(Y) // GCD)
            else:
                tmp = (abs(X) // GCD, abs(Y) // GCD)

            if tmp in dic:
                dic[tmp] += 1
            else:
                dic[tmp] = 1

    for (x, y) in dic.keys():
        if dic.get((-y, x)):
            res += dic[(x, y)] * dic[(-y, x)]

print(res)