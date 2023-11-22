import sys
input = lambda: sys.stdin.readline().rstrip()

stack = []

for ch in input():
    stack.append(ch)
    if stack[-4:] == ['P', 'P', 'A', 'P']:
        for _ in range(3):
            stack.pop()
print("PPAP") if stack == ['P'] else print("NP")