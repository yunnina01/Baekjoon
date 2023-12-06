import sys
input = sys.stdin.readline

N = int(input())
circle = []
for i in range(N):
    x, r = map(int, input().split())
    circle.append((x - r, i))
    circle.append((x + r, i))
circle.sort()

stack = []
for i in range(N * 2):
    d, c = circle[i]
    if len(stack) == 0:
        stack.append(circle[i])
    elif stack:
        if stack[-1][1] == c:
            stack.pop()
        else:
            stack.append(circle[i])
else:
    print("YES") if len(stack) == 0 else print("NO")