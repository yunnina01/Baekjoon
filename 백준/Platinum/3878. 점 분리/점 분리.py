import sys
input = sys.stdin.readline

def ccw(o, a, b):
    return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0])

def convex_hull(points, N):
    points = sorted(points)
    if len(points) <= 1:
        return points
    lower, upper = [], []
    for i in range(N):
        j = N - 1 - i
        while len(lower) >= 2 and ccw(lower[-2], lower[-1], points[i]) <= 0:
            lower.pop()
        lower.append(points[i])
        while len(upper) >= 2 and ccw(upper[-2], upper[-1], points[j]) <= 0:
            upper.pop()
        upper.append(points[j])
    return lower[:-1] + upper[:-1]

def cross_check(line1, line2):
    a1 = ccw(line1[0], line1[1], line2[0])
    a2 = ccw(line1[0], line1[1], line2[1])
    a3 = ccw(line2[0], line2[1], line1[0])
    a4 = ccw(line2[0], line2[1], line1[1])
    flag = 0
    if a1 * a2 <= 0 and a3 * a4 <= 0:
        if a1 * a2 == 0 and a3 * a4 == 0:
            if min(line1[0][0], line1[1][0]) <= max(line2[0][0], line2[1][0]) and min(line1[0][1], line1[1][1]) <= max(line2[0][1], line2[1][1]) \
                and min(line2[0][0], line2[1][0]) <= max(line1[0][0], line1[1][0]) and min(line2[0][1], line2[1][1]) <= max(line1[0][1], line1[1][1]):
                flag = 1
        else:
            flag = 1
    return flag

for _ in range(int(input())):
    n, m = map(int, input().split())
    black = [list(map(int, input().split())) for _ in range(n)]
    white = [list(map(int, input().split())) for _ in range(m)]
    black_convex = convex_hull(black, n)
    white_convex = convex_hull(white, m)
    n, m = len(black_convex), len(white_convex)
    if n < 3 and m < 3:
        print("NO") if cross_check([black_convex[0], black_convex[1 % n]], [white_convex[0], white_convex[1 % m]]) else print("YES")
        continue
    ccw1_count, ccw2_count = [0, 0], [0, 0]
    res = True
    for i in range(n):
        line1 = [black_convex[i], black_convex[(i + 1) % n]]
        for j in range(m):
            ccw1 = ccw(black_convex[i], white_convex[j], white_convex[(j + 1) % m])
            ccw2 = ccw(white_convex[j], black_convex[i], black_convex[(i + 1) % n])
            if ccw1 > 0:
                ccw1_count[0] += 1
            elif ccw1 < 0:
                ccw1_count[1] += 1
            if ccw2 > 0:
                ccw2_count[0] += 1
            elif ccw2 < 0:
                ccw2_count[1] += 1
            line2 = [white_convex[j], white_convex[(j + 1) % m]]
            if cross_check(line1, line2):
                res = False
                break
        if not res:
            break
    
    if max(ccw1_count) == n * m or max(ccw2_count) == n * m:
        res = False 
    print("YES") if res else print("NO")