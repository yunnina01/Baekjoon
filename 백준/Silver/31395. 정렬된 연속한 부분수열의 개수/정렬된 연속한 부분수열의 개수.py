import sys
input = sys.stdin.readline

def getPairCount(n):
    ret = 0
    for i in range(1, n + 1):
        ret += i
    return ret

N = int(input())
A = list(map(int, input().split()))

prev, cnt = 0, 0
res = 0
for a in A:
    if a > prev:
        cnt += 1
    else:
        res += getPairCount(cnt)
        cnt = 1
    prev = a
print(res + getPairCount(cnt))