#include <stdio.h>

char board[20][21];
int visit[26], R, C, res;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

void DFS(int x, int y, int cnt){
    if(cnt > res)
        res = cnt;
    int i, nx, ny;
    visit[board[x][y] - 'A'] = 1;
    for(i = 0; i < 4; i++){
        nx = x + dx[i], ny = y + dy[i];
        if(nx >= 0 && nx < R && ny >= 0 && ny < C && !visit[board[nx][ny] - 'A']){
            DFS(nx, ny, cnt + 1);
            visit[board[nx][ny] - 'A'] = 0;
        }
    }
}

int main(){
    scanf("%d %d", &R, &C);
    for(int i = 0; i < R; i++)
        scanf("%s", board[i]);
    DFS(0, 0, 1);
    printf("%d\n", res);

    return 0;
}