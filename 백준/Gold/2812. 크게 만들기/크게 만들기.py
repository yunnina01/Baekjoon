import sys
input = sys.stdin.readline

N, K = map(int, input().split())
num = input().rstrip()
stack = []

for i in num:
    while K and stack and i > stack[-1]:
        stack.pop()
        K -= 1
    stack.append(i)
print(''.join(stack[:len(stack) - K]))