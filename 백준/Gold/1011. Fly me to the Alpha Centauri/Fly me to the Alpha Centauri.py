import sys
input = sys.stdin.readline

for _ in range(int(input())):
    x, y = map(int, input().split())
    d = y - x
    n = 0
    while 1:
        if d <= n * (n + 1):
            break
        n += 1
    print(n * 2 - 1) if d <= n ** 2 else print(n * 2)