import sys
input = sys.stdin.readline

def find_permutations(n):
    if n in cache:
        return cache[n]
    if n <= 1:
        return 1
    cache[n] = n * find_permutations(n - 1)
    return cache[n]

N = int(input())
data = list(map(int, input().split()))
cache = {}

if data[0] == 1:
    k = data[1]
    arr = [i for i in range(1, N + 1)]
    res = []

    for i in range(N):
        p = find_permutations(N - 1 - i)
        step = (k - 1) // p
        res.append(arr[step])
        arr.remove(arr[step])
        k -= p * step
    print(*res)
else:
    input_permu = data[1:]
    sort_permu = sorted(data[1:])
    res = 1

    for i in range(N):
        step = sort_permu.index(input_permu[i])
        sort_permu.remove(input_permu[i])
        p = find_permutations(N - 1 - i)
        res += p * step
    print(res)