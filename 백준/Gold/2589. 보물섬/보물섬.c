#include <stdio.h>

typedef struct{
    int x, y;
}Pair;

Pair queue[2500];
char map[50][51];
int visit[50][50], N, M, res;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

int max(int a, int b){
    return a > b ? a : b;
}

void BFS(int x, int y){
    int i, j, nx, ny, front = 0, rear = 0, hour = 0, back;
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            if(map[i][j] == 'W')
                visit[i][j] = 1;
            else
                visit[i][j] = 0;
        }
    }
    queue[rear++] = (Pair){x, y};
    visit[x][y] = 1;
    while(1){
        back = rear;
        while(front < back){
            Pair cur = queue[front++];
            for(i = 0; i < 4; i++){
                nx = cur.x + dx[i], ny = cur.y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visit[nx][ny]){
                    queue[rear++] = (Pair){nx, ny};
                    visit[nx][ny] = 1;
                }
            }
        }
        if(rear == back){
            res = max(res, hour);
            return;
        }
        hour++;
    }
}

int main(){
    int i, j;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++)
            scanf(" %c", &map[i][j]);
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            if(map[i][j] == 'L')
                BFS(i, j);
        }
    }
    printf("%d\n", res);

    return 0;
}