import sys, heapq
input = sys.stdin.readline

N = int(input())
gas = []
for _ in range(N):
    heapq.heappush(gas, list(map(int, input().split())))
L, P = map(int, input().split())
heap = []
res = 0

while P < L:
    while gas and gas[0][0] <= P:
        a, b = heapq.heappop(gas)
        heapq.heappush(heap, [-b, a])
    if not heap:
        res = -1
        break
    b, a = heapq.heappop(heap)
    P += -b
    res += 1
print(res)