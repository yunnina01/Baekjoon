import sys
input = sys.stdin.readline

N, M = map(int, input().split())
r, c, d = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
visit = [[0] * M for _ in range(N)]
dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]

visit[r][c] = 1
cnt = 1

while 1:
    flag = 0
    for _ in range(4):
        d = (d + 3) % 4
        nx, ny = r + dx[d], c + dy[d]

        if 0 <= nx < N and 0 <= ny < M and not graph[nx][ny]:
            if not visit[nx][ny]:
                visit[nx][ny] = 1
                cnt += 1
                r, c = nx, ny
                flag = 1
                break

    if not flag:
        if graph[r - dx[d]][c - dy[d]]:
            print(cnt)
            break
        else:
            r, c = r - dx[d], c - dy[d]