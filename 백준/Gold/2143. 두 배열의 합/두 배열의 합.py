import sys
from collections import Counter
input = sys.stdin.readline

T = int(input())
n = int(input())
A = list(map(int, input().split()))
m = int(input())
B = list(map(int, input().split()))
c = Counter()
res = 0

for i in range(n):
    for j in range(i, n):
        c[sum(A[i : j + 1])] += 1

for i in range(m):
    for j in range(i, m):
        res += c[T - sum(B[i : j + 1])]
print(res)