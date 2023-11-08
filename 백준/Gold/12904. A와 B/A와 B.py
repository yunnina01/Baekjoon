import sys
input = sys.stdin.readline

S = list(input().rstrip())
T = list(input().rstrip())
flag = False

while T:
    if T[-1] == 'A':
        T.pop()
    else:
        T.pop()
        T.reverse()
    if S == T:
        flag = True
        break
print(1) if flag else print(0)