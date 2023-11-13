import sys
input = lambda: sys.stdin.readline().rstrip()

class Trie:
    def __init__(self):
        self.root = {}
    
    def add(self, foods):
        cur = self.root
        for food in foods:
            if food not in cur:
                cur[food] = {}
            cur = cur[food]
        cur[0] = True
    
    def travel(self, level, cur):
        if 0 in cur:
            return
        for child in sorted(cur):
            print("--" * level + child)
            self.travel(level + 1, cur[child])

N = int(input())
trie = Trie()
for i in range(N):
    trie.add(list(input().split())[1:])
trie.travel(0, trie.root)