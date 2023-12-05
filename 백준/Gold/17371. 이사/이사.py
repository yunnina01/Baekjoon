import sys
input = sys.stdin.readline

N = int(input())
pos = []
dmax = 10 ** 12
res = [dmax, 0]

def calc(pos1, pos2):
    return (pos1[0] - pos2[0]) ** 2 + (pos1[1] - pos2[1]) ** 2

for _ in range(N):
    pos.append(list(map(int, input().split())))

for i in range(N):
    m = 0
    for j in range(N):
        if i == j:
            continue
        m = max(m, calc(pos[i], pos[j]))
    if m < res[0]:
        res = [m, i]
print(pos[res[1]][0], pos[res[1]][1])