import sys
input = sys.stdin.readline

N = int(input())
date = []
for _ in range(N):
    sm, sd, em, ed = map(int, input().split())
    date.append([sm * 100 + sd, em * 100 + ed])
date.sort(key = lambda x : (x[0], x[1]))
cnt, end, target = 0, 0, 301

while date:
    if target >= 1201 or target < date[0][0]:
        break
    for _ in range(len(date)):
        if target >= date[0][0]:
            if end <= date[0][1]:
                end = date[0][1]
            date.remove(date[0])
        else:
            break
    target = end
    cnt += 1
print(0) if target < 1201 else print(cnt)