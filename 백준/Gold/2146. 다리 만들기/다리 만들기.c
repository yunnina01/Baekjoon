#include <stdio.h>

typedef struct{
    int x, y;
}Pair;

Pair queue[10000];
int map[100][100], visit[100][100], N, lbl, res = 200;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

void DFS(int x, int y){
    int i, nx, ny;
    map[x][y] = lbl;
    visit[x][y] = 1;
    for(i = 0; i < 4; i++){
        nx = x + dx[i], ny = y + dy[i];
        if(nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] && !visit[nx][ny])
            DFS(nx, ny);
    }
}

void BFS(int x, int y, int idx){
    int i, nx, ny, rear = 0;
    for(i = 0; i < 4; i++){
        nx = x + dx[i], ny = y + dy[i];
        if(nx >= 0 && nx < N && ny >= 0 && ny < N && !map[nx][ny])
            queue[rear++] = (Pair){nx, ny};
    }
    if(!rear)
        return;
    int j, front = 0, back, dis = 0;
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            visit[i][j] = 0;
    }
    visit[x][y] = 1;
    for(i = 0; i < rear; i++)
        visit[queue[i].x][queue[i].y] = 1;
    while(1){
        if(dis > res)
            return;
        back = rear;
        while(front < back){
            Pair cur = queue[front++];
            if(map[cur.x][cur.y]){
                if(dis < res)
                    res = dis;
                return;
            }
            for(i = 0; i < 4; i++){
                nx = cur.x + dx[i], ny = cur.y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != idx && !visit[nx][ny]){
                    visit[nx][ny] = 1;
                    queue[rear++] = (Pair){nx, ny};
                }
            }
        }
        dis++;
    }
}

int main(){
    int i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%d", &map[i][j]);
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++){
            if(map[i][j] && !visit[i][j]){
                lbl++;
                DFS(i, j);
            }
        }
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++){
            if(map[i][j])
                BFS(i, j, map[i][j]);
        }
    }
    printf("%d\n", res);

    return 0;
}