import sys
input = sys.stdin.readline

m, M = map(int, input().split())
square = [i ** 2 for i in range(2, int(M ** 0.5) + 1)]
prime = [1] * (M - m + 1)

for i in square:
    n = m // i * i
    while n <= M:
        if n - m >= 0:
            prime[n - m] = 0
        n += i
print(sum(prime))