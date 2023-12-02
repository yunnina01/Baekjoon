def ternarySearch(left, right):
    while abs(right - left) > 1e-9:
        mid1 = (2 * left + right) / 3
        mid2 = (left + 2 * right) / 3
        if dist(mid1) > dist(mid2):
            left = mid1
        else:
            right = mid2
    return dist(left)

def dist(t):
    Mx = Ax * t + Bx * (1 - t)
    My = Ay * t + By * (1 - t)
    Kx = Cx * t + Dx * (1 - t)
    Ky = Cy * t + Dy * (1 - t)
    return ((Kx - Mx) ** 2 + (Ky - My) ** 2) ** 0.5

Ax, Ay, Bx, By, Cx, Cy, Dx, Dy = map(int, input().split())
print("%.10f" % ternarySearch(0, 1)) 