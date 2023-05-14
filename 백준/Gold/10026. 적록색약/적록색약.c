#include <stdio.h>

char grid[100][101];
int visit[100][100], N, res, RGres;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

void DFS(int x, int y, char color){
    int i, nx, ny;
    visit[x][y] = 1;
    for(i = 0; i < 4; i++){
        nx = x + dx[i], ny = y + dy[i];
        if(nx >= 0 && nx < N && ny >= 0 && ny < N && grid[nx][ny] == color && !visit[nx][ny])
            DFS(nx, ny, color);
    }
}

int main(){
    int i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%s", grid[i]);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++){
            if(!visit[i][j]){
                DFS(i, j, grid[i][j]);
                res++;
            }
        }
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++){
            if(grid[i][j] == 'G')
                grid[i][j] = 'R';
            visit[i][j] = 0;
        }
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++){
            if(!visit[i][j]){
                DFS(i, j, grid[i][j]);
                RGres++;
            }
        }
    }
    printf("%d %d\n", res, RGres);

    return 0;
}