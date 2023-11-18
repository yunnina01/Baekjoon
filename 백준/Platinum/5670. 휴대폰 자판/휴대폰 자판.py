import sys
input = lambda: sys.stdin.readline().rstrip()

class Node:
    def __init__(self, key, count = 0):
        self.key = key
        self.child = {}
        self.count = count

class Trie:
    def __init__(self):
        self.root = Node(None)

    def add(self, word):
        cur = self.root
        for w in word:
            if w not in cur.child:
                cur.child[w] = Node(w)
            cur = cur.child[w]
            cur.count += 1
        cur.child['*'] = True
    
def find(l, cur):
    global cnt
    if len(cur.child) > 1 or l == 0:
        for c in cur.child:
            if c != '*':
                cnt += cur.child[c].count
    for c in cur.child:
        if c != '*':
            find(l + 1, cur.child[c])

try:
    while 1:
        N = int(input())
        trie = Trie()
        cnt = 0
        for _ in range(N):
            trie.add(input())
        find(0, trie.root)
        print(f"{round((cnt / N), 2):.2f}")
except:
    exit(0)