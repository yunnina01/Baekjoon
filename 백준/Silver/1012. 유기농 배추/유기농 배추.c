#include <stdio.h>

int graph[50][50], M, N;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

void DFS(int x, int y){
    int i, nx, ny;
    graph[x][y] = 0;
    for(i = 0; i < 4; i++){
        nx = x + dx[i], ny = y + dy[i];
        if(nx >= 0 && ny >= 0 && nx < N && ny < M && graph[nx][ny])
            DFS(nx, ny);
    }
}

int main(){
    int T, K, X, Y, i, j, cnt;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d %d", &M, &N, &K);
        while(K--){
            scanf("%d %d", &X, &Y);
            graph[Y][X] = 1;
        }
        cnt = 0;
        for(i = 0; i < N; i++){
            for(j = 0; j < M; j++){
                if(graph[i][j]){
                    DFS(i, j);
                    cnt++;
                }
            }
        }
        printf("%d\n", cnt);
    }

    return 0;
}