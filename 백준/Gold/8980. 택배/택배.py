import sys
input = sys.stdin.readline

N, C = map(int, input().split())
M = int(input())
infos = []
for _ in range(M):
    infos.append(list(map(int, input().split())))
box = [C] * N
res = 0

infos.sort(key = lambda x : x[1])
for s, r, b in infos:
    tmp = C
    for i in range(s, r):
        tmp = min(tmp, box[i])
    tmp = min(tmp, b)
    for i in range(s, r):
        box[i] -= tmp
    res += tmp
print(res)