import sys
input = sys.stdin.readline

N = int(input())
vilage = [[] for _ in range(N)]
pp, cnt = 0, 0
for i in range(N):
    X, A = map(int, input().split())
    vilage[i] = [X, A]
    pp += A

vilage.sort(key = lambda x : x[0])
for i in range(N):
    cnt += vilage[i][1]
    if cnt >= pp / 2:
        print(vilage[i][0])
        break