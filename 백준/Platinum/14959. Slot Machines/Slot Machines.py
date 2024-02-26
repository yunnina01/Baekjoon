import sys
input = sys.stdin.readline

def fail(s):
    j = 0
    pi = [0] * len(s)
    for i in range(1, len(s)):
        while j and (s[j] != s[i]):
            j = pi[j - 1]
        pi[i] = j = j + (s[j] == s[i])
    return pi

input()
S = list(map(int, input().split()))
lenS = len(S)
S.reverse()
SP = fail(S)

canK = 0
canP = 0
kp = float("inf")
for i, c in enumerate(S):
    k = lenS - (i + 1)
    p = (i + 1) - SP[i]
    if k + p < kp : 
        kp = k + p
        canK = k
        canP = p
print(canK, canP)