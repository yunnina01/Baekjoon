import sys
from functools import cmp_to_key
input = sys.stdin.readline

input()
nums = input().split()
nums.sort(key = cmp_to_key(lambda x, y : -1 if int(x + y) > int(y + x) else 1))
print(0 if sum(map(int, nums)) == 0 else "".join(nums))