import sys
input = lambda: sys.stdin.readline().rstrip()

def manacher(S, N):
    A = [0] * N
    r, p = 0, 0
    for i in range(N):
        if i <= r:
            A[i] = min(A[2 * p - i], r - i)
        while i - A[i] - 1 >= 0 and i + A[i] + 1 < N and S[i - A[i] - 1] == S[i + A[i] + 1]:
            A[i] += 1
        if r < i + A[i]:
            r = i + A[i]
            p = i
    return A

S = '#' + '#'.join(input()) + '#'
print(max(manacher(S, len(S))))