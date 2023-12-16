import sys
input = sys.stdin.readline
read = lambda: map(int, input().split())
cnt = 0

def isIn(Xp, Yp):
    global cnt
    sign1 = (Xa - Xp) * (Yc - Yp) - (Xc - Xp) * (Ya - Yp)
    sign2 = (Xc - Xp) * (Yb - Yp) - (Xb - Xp) * (Yc - Yp)
    sign3 = (Xb - Xp) * (Ya - Yp) - (Xa - Xp) * (Yb - Yp)
    if (sign1 >= 0 and sign2 >= 0 and sign3 >= 0) or (sign1 <= 0 and sign2 <= 0 and sign3 <= 0):
        cnt +=1
        return
    else:
        return

Xa, Ya = read()
Xb, Yb = read()
Xc, Yc = read()
for _ in range(int(input())):
    x, y = read()
    isIn(x, y)
s = abs(Xa * (Yb - Yc) + Xb * (Yc - Ya) + Xc * (Ya - Yb)) / 2

print(round(s, 1))
print(cnt)