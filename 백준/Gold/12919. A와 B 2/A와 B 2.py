import sys
input = lambda: sys.stdin.readline().rstrip()

def DFS(t):
    if t == S:
        print(1)
        exit()
    if len(t) == 0:
        return 0
    if t[-1] == 'A':
        DFS(t[:-1])
    if t[0] == 'B':
        DFS(t[1:][::-1])

S = list(input())
T = list(input())
DFS(T)
print(0)