import sys
input = sys.stdin.readline

def getSmallestSquare(time):
    minX, minY = 1e7, 1e7
    maxX, maxY = -1e7, -1e7

    for i in range(N):
        minX = min(minX, mice[i][0] + time * mice[i][2])
        minY = min(minY, mice[i][1] + time * mice[i][3])
        maxX = max(maxX, mice[i][0] + time * mice[i][2])
        maxY = max(maxY, mice[i][1] + time * mice[i][3])
    return max(maxX - minX, maxY - minY)
    
N = int(input())
mice = []
for _ in range(N):
    mice.append(list(map(float, input().split())))

start, end = 0.0, 2000.0
for i in range(1234):
    p = start + (end - start) / 3
    q = start + (end - start) / 3 * 2

    if getSmallestSquare(p) >= getSmallestSquare(q):
        start = p
    else:
        end = q

res = getSmallestSquare(start)
print(f'{res:.10f}')