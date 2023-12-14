import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
bridges = [[] for _ in range(N + 1)]
for _ in range(M):
    A, B, C = map(int, input().split())
    bridges[A].append([B, C])
    bridges[B].append([A, C])
one, two = map(int, input().split())

def BFS(start, end, weight):
    dq = deque([start])
    visit = [0] * (N + 1)
    visit[start] = 1
    while dq:
        x = dq.popleft()
        if x == end:
            return 1
        for b in bridges[x]:
            if not visit[b[0]] and b[1] >= weight:
                visit[b[0]] = 1
                dq.append(b[0])
    return 0

left, right = 1, 1000000000
while left <= right:
    mid = (left + right) >> 1
    if BFS(one, two, mid):
        res = mid
        left = mid + 1
    else:
        right = mid - 1
print(res)