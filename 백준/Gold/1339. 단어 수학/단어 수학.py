import sys
input = sys.stdin.readline

alpha, alpha_dic, lst = [], {}, []
res, pows = 0, 9

N = int(input())
for _ in range(N):
    alpha.append(input().rstrip())
for i in range(N):
    for j in range(len(alpha[i])):
        if alpha[i][j] in alpha_dic:
            alpha_dic[alpha[i][j]] += 10 ** (len(alpha[i]) - j - 1)
        else:
            alpha_dic[alpha[i][j]] = 10 ** (len(alpha[i]) - j - 1)
for i in alpha_dic.values():
    lst.append(i)
lst.sort(reverse = True)

for i in lst:
    res += pows * i
    pows -= 1
print(res)