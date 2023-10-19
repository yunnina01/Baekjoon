N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))
S = 0

A.sort()
for a in A:
    S += (a * max(B))
    B.remove(max(B))
print(S)