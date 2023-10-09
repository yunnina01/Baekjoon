import sys
input = sys.stdin.readline

board = [input().rstrip() for _ in range(5)]
visit = [[0] * 5 for _ in range(5)]
dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]
p = []
res = 0

def check(s):
    global visit
    x, y = s % 5, s // 5
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < 5 and 0 <= ny < 5:
            if not visit[ny][nx]:
                if(ny * 5 + nx) in p:
                    visit[ny][nx] = 1
                    check(ny * 5 + nx)

def DFS(cnt, idx, yn):
    global visit, res
    if yn >= 4 or 25 - idx < 7 - cnt:
        return
    if cnt == 7:
        check(p[0])
        if sum(sum(visit, [])) == 7:
            res += 1
        visit = [[0] * 5 for _ in range(5)]
        return
    x, y = idx % 5, idx // 5
    p.append(idx)
    if board[y][x] == 'Y':
        DFS(cnt + 1, idx + 1, yn + 1)
    else:
        DFS(cnt + 1, idx + 1, yn)
    p.pop()
    DFS(cnt, idx + 1, yn)

DFS(0, 0, 0)
print(res)