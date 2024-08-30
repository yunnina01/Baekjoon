import sys
input = sys.stdin.readline

N = int(input())
numbers = list(map(int, input().split()))

res = [0] * (N + 1)
for num in numbers:
    res[num] = res[num - 1] + 1
print(N - max(res))