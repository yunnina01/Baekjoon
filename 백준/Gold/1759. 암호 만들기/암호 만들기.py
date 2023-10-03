import sys
input = sys.stdin.readline

L, C = map(int, input().split())
alpha = sorted(list(map(str, input().split())))
res = []

def backtracking(cnt, idx):
    if cnt == L:
        vo, co = 0, 0
        for i in range(L):
            if res[i] in ['a', 'e', 'i', 'o', 'u']:
                vo += 1
            else:
                co += 1
        if vo >= 1 and co >= 2:
            print(''.join(res))
        return
    for i in range(idx, C):
        res.append(alpha[i])
        backtracking(cnt + 1, i + 1)
        res.pop()

backtracking(0, 0)