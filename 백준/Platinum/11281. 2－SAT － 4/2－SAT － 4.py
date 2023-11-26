import sys
input = lambda: sys.stdin.readline().rstrip()
read = lambda: map(int, input().split())
sys.setrecursionlimit(10 ** 5)

def SCC(node):
    global idx, scc_num
    visit[node] = idx
    root = idx
    idx += 1
    stack.append(node)

    for next in graph[node]:
        if not visit[next]:
            root = min(root, SCC(next))
        elif not check[next]:
            root = min(root, visit[next])

    if root == visit[node]:
        while stack:
            top = stack.pop()
            check[top] = 1
            scc_idx[top] = scc_num
            if node == top:
                break
        scc_num += 1
    return root

N, M = read()
graph = [[] for _ in range(2 * N + 1)]
for _ in range(M):
    i, j = read()
    graph[-i].append(j)
    graph[-j].append(i)

scc_idx = [0] * (2 * N + 1)
visit = [0] * (2 * N + 1)
check = [0] * (2 * N + 1)
stack = []
scc_num, idx = 1, 1

for i in range(1, 2 * N + 1):
    if not visit[i]:
        SCC(i)

res = [0] * N
for i in range(1, N + 1):
    if scc_idx[i] == scc_idx[-i]:
        print(0)
        break
    if scc_idx[i] < scc_idx[-i]:
        res[i - 1] = 1
else:
    print(1)
    print(*res)