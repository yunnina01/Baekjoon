import sys
input = sys.stdin.readline

N, M, L = map(int, input().split())
rest = [0] + list(map(int, input().split())) + [L]
rest.sort()
res = 0

left, right = 1, L - 1
while left <= right:
    cnt = 0
    mid = (left + right) >> 1
    for i in range(1, len(rest)):
        if rest[i] - rest[i - 1] > mid:
            cnt += (rest[i] - rest[i - 1] - 1) // mid
    if cnt > M:
        left = mid + 1
    else:
        right = mid - 1
        res = mid
print(res)