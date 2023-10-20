import sys
input = sys.stdin.readline

N, K = map(int, input().split())
products = list(map(int, input().split()))
plugs = []
res = 0

for i in range(K):
    if products[i] in plugs:
        continue
    if len(plugs) < N:
        plugs.append(products[i])
        continue
    mt = []
    for j in plugs:
        if j in products[i :]:
            mt.append(products[i :].index(j))
        else:
            mt.append(101)
    plugs.remove(plugs[mt.index(max(mt))])
    plugs.append(products[i])
    res += 1
print(res)