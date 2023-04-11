#include <stdio.h>

int dp[500][500], map[500][500], M, N;
int dx[] = {1, 0, -1, 0}, dy[] = {0, 1, 0, -1};

int DFS(int x, int y){
    if(x == M - 1 && y == N - 1)
        return 1;
    if(dp[x][y] != -1)
        return dp[x][y];
    dp[x][y] = 0;
    int i, nx, ny;
    for(i = 0; i < 4; i++){
        nx = x + dx[i], ny = y + dy[i];
        if(nx >= 0 && nx < M && ny >= 0 && ny < N){
            if(map[x][y] > map[nx][ny])
                dp[x][y] += DFS(nx, ny);
        }
    }
    return dp[x][y];
}

int main(){
    int i, j;
    scanf("%d %d", &M, &N);
    for(i = 0; i < M; i++){
        for(j = 0; j < N; j++){
            scanf("%d", &map[i][j]);
            dp[i][j] = -1;
        }
    }
    printf("%d\n", DFS(0, 0));

    return 0;
}