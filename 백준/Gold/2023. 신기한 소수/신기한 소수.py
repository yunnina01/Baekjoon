import sys
input = sys.stdin.readline

N = int(input())

def is_prime(num):
    if num < 2:
        return 0
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            return 0
    return 1

def DFS(num):
    if len(str(num)) == N:
        print(num)
    else:
        for i in range(10):
            temp = num * 10 + i
            if is_prime(temp):
                DFS(temp)

DFS(2);DFS(3);DFS(5);DFS(7)