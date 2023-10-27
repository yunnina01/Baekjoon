import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))
S = int(input())

for i in range(N):
    idx = A.index(max(A[i : min(N, S + i + 1)]))
    for j in range(idx, i, -1):
        A[j], A[j - 1] = A[j - 1], A[j]
    S -= idx - i
    if S <= 0:
        break
print(*A)