import sys

def input():
    return sys.stdin.readline().rstrip()

def find(x):
    if gate[x] == x:
        return x
    gate[x] = find(gate[x])
    return gate[x]

G = int(input())
P = int(input())
gate = [i for i in range(G + 1)]
plane = []
cnt = 0
for _ in range(P):
    plane.append(int(input()))

for p in plane:
    tmp = find(p)
    if not tmp:
        break
    gate[tmp] = gate[tmp - 1]
    cnt += 1
print(cnt)