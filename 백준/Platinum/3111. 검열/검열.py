import sys
input = lambda: sys.stdin.readline().rstrip()

A = list(input())
T = input()
rev_A = list(reversed(A))
front, back = [], []
low, high = 0, len(T) - 1
len_A, flag = len(A), True

while low <= high:
    if flag:
        front.append(T[low])
        low += 1
        if front[-len_A:] == A:
            front[-len_A:] = []
            flag = False
    else:
        back.append(T[high])
        high -= 1
        if back[-len_A:] == rev_A:
            back[-len_A:] = []
            flag = True
    
while back:
    front.append(back.pop())
    if front[-len_A:] == A:
        front[-len_A:] = []
print(''.join(front))