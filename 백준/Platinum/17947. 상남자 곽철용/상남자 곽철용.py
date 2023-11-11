import sys
input = sys.stdin.readline

N, M, K = map(int, input().split())
card = set(range(1, 4 * N + 1))
for _ in range(M):
    a, b = map(int, input().split())
    card.discard(a)
    card.discard(b)
A, B = map(int, input().split())

card.discard(A)
card.discard(B)
score = abs(A % K - B % K)
card = list(card)
for i in range(len(card)):
    card[i] %= K
card.sort()
l, r = 0, len(card) // 2
res = 0

while r < len(card) and res < M - 1:
    if card[r] - card[l] > score:
        l += 1
        res += 1
    r += 1
print(res)