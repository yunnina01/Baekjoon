import sys
from collections import deque
from queue import PriorityQueue
input = sys.stdin.readline

N, M, K = map(int, input().split())
lines, idx = [deque() for _ in range(M)], 0
for i in range(N):
    D, H = map(int, input().split())
    line = idx % M
    lines[line].append((-D, -H, line, i + 1))
    idx += 1
heap = PriorityQueue()

for i in range(M):
    if lines[i]:
        heap.put(lines[i].popleft())

worker, cnt = -1, 0
while worker != (K + 1):
    _, _, line, worker = heap.get()
    cnt += 1
    if lines[line]:
        heap.put(lines[line].popleft())
print(cnt - 1)