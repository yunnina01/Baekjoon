#include <stdio.h>

typedef struct{
    int x, y;
}Pair;

Pair queue[10000];
int graph[101][101], N, M, front, rear, res;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

void BFS(){
    int i, nx, ny, back;
    queue[rear++] = (Pair){1, 1};
    graph[1][1] = 0;
    while(1){
        back = rear, res++;
        while(front != back){
            Pair cur = queue[front++];
            if(cur.x == N && cur.y == M)
                return;
            for(i = 0; i < 4; i++){
                nx = cur.x + dx[i], ny = cur.y + dy[i];
                if(nx > 0 && ny > 0 && nx <= N && ny <= M && graph[nx][ny]){
                    queue[rear++] = (Pair){nx, ny};
                    graph[nx][ny] = 0;
                }
            }
        }
    }
}

int main(){
    int i, j;
    scanf("%d %d", &N, &M);
    for(i = 1; i <= N; i++){
        for(j = 1; j <= M; j++)
            scanf("%1d", &graph[i][j]);
    }
    BFS();
    printf("%d\n", res);

    return 0;
}