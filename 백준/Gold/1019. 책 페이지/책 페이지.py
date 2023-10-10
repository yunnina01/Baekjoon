import sys
input = sys.stdin.readline

N = int(input())
cnt = [0] * 10
digit = 1

def one_digit(n):
    while n % 10 != 9:
        for i in str(n):
            cnt[int(i)] += digit
        n -= 1
    return n

while N > 0:
    N = one_digit(N)
    if N < 10:
        for i in range(N + 1):
            cnt[i] += digit
    else:
        for i in range(10):
            cnt[i] += (N // 10 + 1) * digit
    cnt[0] -= digit
    digit *= 10
    N //= 10

print(*cnt)