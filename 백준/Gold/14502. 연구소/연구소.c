#include <stdio.h>

typedef struct{
    int x, y;
}Pair;

Pair queue[61];
int map[8][8], visit[8][8], N, M, idx, res;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

void BFS(){
    int i, j, nx, ny, front = 0, rear = 0, cnt = 0;
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            visit[i][j] = map[i][j];
            if(map[i][j] == 2)
                queue[rear++] = (Pair){i, j};
        }
    }
    while(front < rear){
        Pair cur = queue[front++];
        for(i = 0; i < 4; i++){
            nx = cur.x + dx[i], ny = cur.y + dy[i];
            if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visit[nx][ny]){
                visit[nx][ny] = 2;
                queue[rear++] = (Pair){nx, ny};
            }
        }
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            if(!visit[i][j])
                cnt++;
        }
    }
    if(cnt > res)
        res = cnt;
}

int main(){
    int i, j, k;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++)
            scanf("%d", &map[i][j]);
    }
    for(i = 0; i < N * M; i++){
        if(!map[i / M][i % M]){
            map[i / M][i % M] = 1;
            for(j = i + 1; j < N * M; j++){
                if(!map[j / M][j % M]){
                    map[j / M][j % M] = 1;
                    for(k = j + 1; k < N * M; k++){
                        if(!map[k / M][k % M]){
                            map[k / M][k % M] = 1;
                            BFS();
                            map[k / M][k % M] = 0;
                        }
                    }
                    map[j / M][j % M] = 0;
                }
            }
            map[i / M][i % M] = 0;
        }
    }
    printf("%d\n", res);

    return 0;
}