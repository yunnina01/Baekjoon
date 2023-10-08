import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().split()))
lst = []
res = 0

if N == 1:
    nums.sort()
    res = sum(nums[: 5])
else:
    lst.append(min(nums[0], nums[5]))
    lst.append(min(nums[1], nums[4]))
    lst.append(min(nums[2], nums[3]))
    lst.sort()

    min1, min2, min3 = lst[0], sum(lst[: 2]), sum(lst[: 3])

    res += ((N - 2) * (N - 2) + 4 * (N - 1) * (N - 2)) * min1
    res += (4 * (N - 2) + 4 * (N - 1)) * min2
    res += 4 * min3

print(res)