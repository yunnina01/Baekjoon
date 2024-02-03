import sys
input = sys.stdin.readline

A = input().rstrip()
B = input().rstrip()
C = int(input())

AB = int(A + B)
A = int(A)
B = int(B)
print(A + B - C)
print(AB - C)