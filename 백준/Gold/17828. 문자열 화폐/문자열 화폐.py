import sys
input = sys.stdin.readline

N, X = map(int, input().split())

if X < N or X > N * 26:
    print('!')
else:
    z = (X - N) // 25
    mid = chr((X - N) % 25 + 65)
    if z == N:
        mid = ''
    a = N - z - 1
    print('A' * a + mid + 'Z' * z)