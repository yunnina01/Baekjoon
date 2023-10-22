import sys
input = sys.stdin.readline

N = int(input())
K = int(input())
sensor = list(map(int, input().rstrip().split()))
interval = []

sensor.sort()
for i in range(1, N):
    interval.append(sensor[i] - sensor[i - 1])
interval.sort()
print(sum(interval[:N - K]))