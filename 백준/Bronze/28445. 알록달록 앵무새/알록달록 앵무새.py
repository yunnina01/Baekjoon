dad = list(input().split())
mom = list(input().split())
color = sorted(set(dad + mom))
for i in color:
    for j in color:
        print(i, j)