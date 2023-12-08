import sys
input = sys.stdin.readline

N = int(input())
Q = [[*map(int, input().split())]]
for _ in range(N - 1):
    x, y = map(int, input().split())
    if Q[-1][1] * y < 0:
        Q.append([x, y])
if Q[0][1] * Q[-1][1] > 0:
    Q.pop(0)
if Q[0][1] < 0:
    Q.append(Q.pop(0))

stack = []
N = len(Q) // 2
for i in range(N):
    stack.append(sorted([Q[i * 2][0], Q[i * 2 + 1][0]]))
stack.sort()

big = small = 0
end = last = -1e10
for i in range(N):
    s, e = stack[i]
    if s > last:
        small += 1
    if s > end:
        big += 1
        end = e
    last = e
print(big, small)