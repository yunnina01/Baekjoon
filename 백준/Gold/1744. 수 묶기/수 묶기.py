import sys
input = sys.stdin.readline

plus, minus = [], []
res = 0

for _ in range(int(input())):
    n = int(input())
    if n > 1:
        plus.append(n)
    elif n <= 0:
        minus.append(n)
    else:
        res += 1
plus.sort(reverse = True)
minus.sort()

if len(plus) % 2:
    plus.append(1)
if len(minus) % 2:
    minus.append(1)
for i in range(0, len(plus), 2):
    res += (plus[i] * plus[i + 1])
for i in range(0, len(minus), 2):
    res += (minus[i] * minus[i + 1])
print(res)