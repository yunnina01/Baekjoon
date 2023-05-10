#include <stdio.h>
#define SIZE 1000000

typedef struct{
    int x, y, chk, dis;
}Pos;

Pos queue[SIZE];
int map[1000][1000], visit[1000][1000][2], N, M, front, rear;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

int BFS(){
    queue[rear++] = (Pos){0, 0, 0, 1};
    while(front != rear){
        Pos cur = queue[front++];
        front %= SIZE;
        if(cur.x == N - 1 && cur.y == M - 1)
            return cur.dis;
        if(cur.x >= 0 && cur.y >= 0 && cur.x < N && cur.y < M && !visit[cur.x][cur.y][cur.chk]){
            if(cur.chk + map[cur.x][cur.y] < 2){
                visit[cur.x][cur.y][cur.chk] = 1;
                cur.dis++;
                for(int i = 0; i < 4; i++){
                    queue[rear++] = (Pos){cur.x + dx[i], cur.y + dy[i], cur.chk + map[cur.x][cur.y], cur.dis};
                    rear %= SIZE;
                }
            }
        }
    }
    return -1;
}

int main(){
    int i, j;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++)
            scanf("%1d", &map[i][j]);
    }
    printf("%d\n", BFS());

    return 0;
}