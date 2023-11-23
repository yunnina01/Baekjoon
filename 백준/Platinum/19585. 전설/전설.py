import sys
input = lambda: sys.stdin.readline().rstrip()

def check(word):
    cur = colors
    for i in range(len(word)):
        if cur.get(0) and word[i:] in teams:
            return 1
        if not cur.get(word[i]):
            return
        cur = cur[word[i]]

C, N = map(int, input().split())
colors = {}
for _ in range(C):
    cur = colors
    for c in input():
        if not cur.get(c):
            cur[c] = {}
        cur = cur[c]
    cur[0] = 1
teams = {input() for i in range(N)}

for _ in range(int(input())):
    print("Yes" if check(input()) else "No")