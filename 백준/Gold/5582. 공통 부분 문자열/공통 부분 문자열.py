import sys
input = lambda: sys.stdin.readline().rstrip()

s, t = input(), input()
start, end = 0, 1
res = 0

while end <= len(s):
    if s[start:end] in t:
        end += 1
    else:
        start += 1
    res = max(res, end - start)
print(res - 1)