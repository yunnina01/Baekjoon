import sys
input = sys.stdin.readline

N = int(input())
cranes = sorted(list(map(int, input().split())), reverse = True)
M = int(input())
boxes = sorted(list(map(int, input().split())), reverse = True)
pos, visit = [0] * N, [0] * M
cnt, time = 0, 0

if cranes[0] < boxes[0]:
    time = -1
else:
    while cnt < M:
        for i in range(N):
            while pos[i] < M:
                if not visit[pos[i]] and cranes[i] >= boxes[pos[i]]:
                    visit[pos[i]] = 1
                    pos[i] += 1
                    cnt += 1
                    break
                pos[i] += 1
        time += 1
print(time)