import sys
input = sys.stdin.readline

w = 1
input()
for i in sorted(list(map(int, input().split()))):
    if i > w:
        break
    w += i
print(w)