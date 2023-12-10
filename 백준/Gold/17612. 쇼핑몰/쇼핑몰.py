import sys
import heapq
input = sys.stdin.readline

N, K = map(int, input().split())
before, after = [], []
idx = 1

for _ in range(N):
    pid, w = map(int, input().split())
    if idx <= K:
        heapq.heappush(before, (w, idx, pid))
        idx += 1
    else:
        time, counter, doneid = heapq.heappop(before)
        heapq.heappush(after, (time, -counter, doneid))
        heapq.heappush(before, (time + w, counter, pid))

while before:
    time, counter, doneid = heapq.heappop(before)
    heapq.heappush(after, (time, -counter, doneid))

idx, res = 1, 0
while after:
    _, _, doneid = heapq.heappop(after)
    res += idx * doneid
    idx += 1
print(res)