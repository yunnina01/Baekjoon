import sys
from collections import deque
input = sys.stdin.readline

prime = [1] * 10000
for i in range(2, 100):
    if prime[i]:
        for j in range(2 * i, 10000, i):
            prime[j] = 0

def BFS(s, e):
    dq = deque()
    dq.append([s, 0])
    visit = [0] * 10000
    visit[s] = 1
    while dq:
        cur, cnt = dq.popleft()
        strcur = str(cur)
        if cur == e:
            return cnt
        for i in range(4):
            for j in range(10):
                temp = int(strcur[: i] + str(j) + strcur[i + 1 :])
                if not visit[temp] and prime[temp] and temp >= 1000:
                    visit[temp] = 1
                    dq.append([temp, cnt + 1])

for _ in range(int(input())):
    start, end = map(int, input().split())
    print(BFS(start, end))