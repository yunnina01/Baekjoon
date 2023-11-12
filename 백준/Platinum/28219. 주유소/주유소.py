import sys
input = sys.stdin.readline

N, K = map(int, input().split())
road = [[] for _ in range(N + 1)]
for _ in range(N - 1):
    x, y = map(int, input().split())
    road[x].append(y)
    road[y].append(x)
visit = [False] * (N + 1)
dp = [-1] * (N + 1)
stack = [1]
res = 0

while stack:
    cur = stack[-1]
    if not visit[cur]:
        visit[cur] = True
        for next in road[cur]:
            if not visit[next]:
                stack.append(next)
        continue

    dis1, dis2 = 0, 0
    for next in road[cur]:
        next_dis = dp[next] + 1
        if dis1 <= next_dis:
            dis2 = dis1
            dis1 = next_dis
        elif dis2 <= next_dis:
            dis2 = next_dis
    
    if K <= dis1 + dis2:
        res += 1
        dp[cur] = -1
    else:
        dp[cur] = dis1
    stack.pop()
print(res)