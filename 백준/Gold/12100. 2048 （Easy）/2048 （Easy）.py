from copy import deepcopy
import sys
input = sys.stdin.readline

N = int(input())
board = [list(map(int, input().rstrip().split())) for _ in range(N)]
res = 0

def up(board):
    for j in range(N):
        pointer = 0
        for i in range(1, N):
            if board[i][j]:
                temp = board[i][j]
                board[i][j] = 0
                if board[pointer][j] == 0:
                    board[pointer][j] = temp
                elif board[pointer][j] == temp:
                    board[pointer][j] <<= 1
                    pointer += 1
                else:
                    pointer += 1
                    board[pointer][j] = temp
    return board

def down(board):
    for j in range(N):
        pointer = N - 1
        for i in range(N - 2, -1, -1):
            if board[i][j]:
                temp = board[i][j]
                board[i][j] = 0
                if board[pointer][j] == 0:
                    board[pointer][j] = temp
                elif board[pointer][j] == temp:
                    board[pointer][j] <<= 1
                    pointer -= 1
                else:
                    pointer -= 1
                    board[pointer][j] = temp
    return board

def left(board):
    for i in range(N):
        pointer = 0
        for j in range(1, N):
            if board[i][j]:
                temp = board[i][j]
                board[i][j] = 0
                if board[i][pointer] == 0:
                    board[i][pointer] = temp
                elif board[i][pointer] == temp:
                    board[i][pointer] <<= 1
                    pointer += 1
                else:
                    pointer += 1
                    board[i][pointer]= temp
    return board

def right(board):
    for i in range(N):
        pointer = N - 1
        for j in range(N - 2, -1, -1):
            if board[i][j]:
                temp = board[i][j]
                board[i][j] = 0
                if board[i][pointer] == 0:
                    board[i][pointer] = temp
                elif board[i][pointer] == temp:
                    board[i][pointer] <<= 1
                    pointer -= 1
                else:
                    pointer -= 1
                    board[i][pointer] = temp
    return board

def DFS(board, cnt):
    if cnt == 5:
        return max(map(max, board))
    return max(DFS(up(deepcopy(board)), cnt + 1), DFS(down(deepcopy(board)), cnt + 1), DFS(left(deepcopy(board)), cnt + 1), DFS(right(deepcopy(board)), cnt + 1))

print(DFS(board, 0))