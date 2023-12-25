import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))
A.sort()
res = 0

for i in range(N):
    tmp = A[: i] + A[i + 1 :]
    left, right = 0, len(tmp) - 1
    while left < right:
        p = tmp[left] + tmp[right]
        if p == A[i]:
            res += 1
            break
        if p < A[i]:
            left += 1
        else:
            right -= 1
print(res)