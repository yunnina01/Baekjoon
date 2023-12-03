import sys
input = sys.stdin.readline

Ax, Ay, Az, Bx, By, Bz, Cx, Cy, Cz = map(int, input().split())
res = float('inf')

while True:
    mx, my, mz = (Ax + Bx) / 2, (Ay + By) / 2, (Az + Bz) / 2
    l = ((Ax - Cx) ** 2 + (Ay - Cy) ** 2 + (Az - Cz) ** 2) ** 0.5
    h = ((mx - Cx) ** 2 + (my - Cy) ** 2 + (mz - Cz) ** 2) ** 0.5
    r = ((Bx - Cx) ** 2 + (By - Cy) ** 2 + (Bz - Cz) ** 2) ** 0.5

    if abs(res - h) <= 1e-6:
        print(f'{res:.10f}')
        exit()
    if h < res:
        res = h
    if l > r:
        Ax, Ay, Az = mx, my, mz
    else:
        Bx, By, Bz = mx, my, mz