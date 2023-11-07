import sys
input = sys.stdin.readline

for _ in range(int(input())):
    N, M = map(int, input().split())
    books = [False] * (N + 1)
    req = []
    for _ in range(M):
        req.append(list(map(int, input().split())))
    req.sort(key = lambda x : x[1])
    cnt = 0

    while req:
        a, b = req.pop(0)
        for i in range(a, b + 1):
            if not books[i]:
                cnt += 1
                books[i] = True
                break
    print(cnt)