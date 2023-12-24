import sys
input = sys.stdin.readline
read = lambda: map(int, input().split())

def cross(d1, d2, d3, d4):
    return (d2[0] - d1[0]) * (d4[1] - d3[1]) - (d2[1] - d1[1]) * (d4[0] - d3[0])

def dot(d1, d2, d3, d4):
    return (d2[0] - d1[0]) * (d4[0] - d3[0]) + (d2[1] - d1[1]) * (d4[1] - d3[1])

def isCross(d1, d2, d3, d4):
    if cross(d1, d2, d2, d3) * cross(d2, d1, d1, d4) > 0 and cross(d3, d4, d4, d1) * cross(d4, d3, d3, d2) > 0:
        return True
    elif (not cross(d1, d2, d2, d3) and dot(d1, d3, d3, d2) > 0) or (not cross(d1, d2, d2, d4) and dot(d1, d4, d4, d2) > 0):
        return True
    elif (not cross(d3, d4, d4, d1) and dot(d3, d1, d1, d4) > 0) or (not cross(d3, d4, d4, d2) and dot(d3, d2, d2, d4) > 0):
        return True
    return False

def dfs(x):
    if x == N:
        for i in range(N):
            d1, d2 = robots[i], shelter[perm[i]]
            for j in range(i + 1, N):
                d3, d4 = robots[j], shelter[perm[j]]
                if isCross(d1, d2, d3, d4):
                    return
        for i in range(N):
            print(perm[i] + 1)
        exit(0)
    for i in range(N):
        if not visited[i]:
            visited[i] = 1
            perm[x] = i
            dfs(x + 1)
            visited[i] = 0

N = int(input())
robots = [tuple(read()) for _ in range(N)]
shelter = [tuple(read()) for _ in range(N)]
visited = [0] * N
perm = [0] * N
dfs(0)
print('Impossible')