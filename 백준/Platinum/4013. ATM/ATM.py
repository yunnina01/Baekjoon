import sys
from collections import deque
input = lambda: sys.stdin.readline().rstrip()
read = lambda: map(int, input().split())
sys.setrecursionlimit(650001)

def DFS(node):
    visit[node] = 1
    for now in graph[node]:
        if not visit[now]:
            DFS(now)
    stack.append(node)

def reverse_DFS(node, num):
    scc_num[node] = num
    scc_arr[num] += 1
    scc_val[num] += cash[node]
    for now in reverse_graph[node]:
        if scc_num[now] == -1:
            reverse_DFS(now, num)
        elif scc_num[node] != scc_num[now]:
            group[scc_num[now]].append(scc_num[node])

N, M = read()
graph = [[] for _ in range(N)]
reverse_graph = [[] for _ in range(N)]
visit, scc_num = [0] * N, [-1] * N
stack, scc_arr, group = [], [], []

for i in range(M):
    a, b = read()
    graph[a - 1].append(b - 1)
    reverse_graph[b - 1].append(a - 1)	
cash = [int(input()) for _ in range(N)]

for i in range(N):
	if not visit[i]:
		DFS(i)
		
scc_val = []
k = 0
while stack:
    now = stack.pop()
    if scc_num[now] == -1:
        group.append([])
        scc_arr.append(0)
        scc_val.append(0)
        reverse_DFS(now, k)
        k += 1
		
S, P = read()
S -= 1
rest = list(read())

dq = deque([scc_num[S]])
dp = [0] * k
dp[scc_num[S]] = scc_val[scc_num[S]]
while dq:
	now = dq.popleft()
	for next in group[now]:
		if dp[next] < dp[now] + scc_val[next]:
			dp[next] = dp[now] + scc_val[next]
			dq.append(next)

res = 0
for r in rest:
	res = max(res, dp[scc_num[r - 1]])
print(res)