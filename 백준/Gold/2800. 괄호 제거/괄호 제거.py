import sys
from itertools import combinations
input = lambda: sys.stdin.readline().rstrip()

exp = list(input())
stack, index = [], []
res = set()

for i in range(len(exp)):
    if exp[i] == '(':
        stack.append(i)
    elif exp[i] == ')':
        index.append((stack.pop(), i))
    
for i in range(1, len(index) + 1):
    comb = list(combinations(index, i))
    for c in comb:
        tmp = exp[:]
        for x, y in c:
            tmp[x], tmp[y] = '', ''
        res.add(''.join(tmp))
res = sorted(list(res))
print(*res, sep = '\n')