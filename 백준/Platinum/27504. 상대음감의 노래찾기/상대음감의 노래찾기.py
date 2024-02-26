import sys
input = sys.stdin.readline

N = int(input())
musicList = [list(map(int, input().split(" "))) for _ in range(N)]
L = int(input())
b = list(map(int, input().split(" ")))

melodyKey = []
for i in range(len(b) - 1):
    melodyKey.append(b[i + 1] - b[i])
pi = [0] * len(melodyKey)
matched = 0
for i in range(1, len(melodyKey)):
    key = melodyKey[i]
    if key == melodyKey[matched]:
        matched += 1
        pi[i] = matched
    else:
        while matched != 0 and key != melodyKey[pi[matched - 1]]:
            matched = pi[matched - 1]

foundedList = []
for i, music in enumerate(musicList):
    Ki = music[0]
    matched = 0
    for j in range(1, len(music) - 1):
        key = music[j + 1] - music[j]
        if key == melodyKey[matched]:
            matched += 1
            if matched == len(melodyKey):
                foundedList.append(i + 1)
                break
        else:
            while matched != 0 and key != melodyKey[pi[matched - 1]]:
                matched = pi[matched - 1]

print(*foundedList if foundedList else [-1])