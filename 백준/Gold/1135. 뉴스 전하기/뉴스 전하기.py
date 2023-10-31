import sys
input = sys.stdin.readline

def DP(x):
    child = []
    for node in tree[x]:
        DP(node)
        child.append(dp[node])
    if not tree[x]:
        child.append(0)
    child.sort(reverse = True)
    time = [child[i] + i + 1 for i in range(len(child))]
    dp[x] = max(time)

N = int(input())
super = list(map(int, input().split()))
tree = [[] for _ in range(N)]
for i in range(1, N):
    tree[super[i]].append(i)
dp = [False] * N

DP(0)
print(dp[0] - 1)