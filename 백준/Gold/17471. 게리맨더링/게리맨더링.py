import sys
from itertools import combinations
from collections import deque, defaultdict
input = sys.stdin.readline

N = int(input())
pp = [0] + list(map(int, input().split()))
dict = defaultdict(list)
for i in range(1, N + 1):
    adj = list(map(int, input().split()))
    dict[i].extend(adj[1 :])
res = 1e9

def BFS(arr):
    start = arr[0]
    dq = deque([start])
    visit = [start]
    retsum = 0
    while dq:
        node = dq.popleft()
        retsum += pp[node]
        for i in dict[node]:
            if i not in visit and i in arr:
                dq.append(i)
                visit.append(i)
    return retsum, len(visit)

for i in range(1, N // 2 + 1):
    for comb in combinations(range(1, N + 1), i):
        sum1, len1 = BFS(comb)
        sum2, len2 = BFS([i for i in range(1, N + 1) if i not in comb])
        if len1 + len2 == N:
            res = min(res, abs(sum1 - sum2))
print(-1) if res == 1e9 else print(res)