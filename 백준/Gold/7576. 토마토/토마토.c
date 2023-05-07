#include <stdio.h>

typedef struct{
    int x, y;
}Pair;

Pair queue[1000000];
int visit[1000][1000], front, rear;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

int main(){
    int M, N, i, j, n, cnt = 0, back;
    scanf("%d %d", &M, &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            scanf("%d", &n);
            if(n)
                visit[i][j] = 1;
            if(n == 1)
                queue[rear++] = (Pair){i, j};
        }
    }
    while(1){
        back = rear;
        while(front < back){
            Pair cur = queue[front++], next;
            for(i = 0; i < 4; i++){
                next.x = cur.x + dx[i];
                next.y = cur.y + dy[i];
                if(next.x >= 0 && next.y >= 0 && next.x < N && next.y < M && !visit[next.x][next.y]){
                    visit[next.x][next.y] = 1;
                    queue[rear++] = next;
                }
            }
        }
        if(back == rear)
            break;
        cnt++;
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            if(!visit[i][j]){
                cnt = -1;
                break;
            }
        }
        if(j != M)
            break;
    }
    printf("%d\n", cnt);

    return 0;
}