#include <stdio.h>

typedef struct{
    int x, y;
}Pair;

Pair queue[100000];
int dx[] = {1, 2, 2, 1, -1, -2, -2, -1}, dy[] = {2, 1, -1, -2, -2, -1, 1, 2};

int main(){
    int T, I, X, Y;
    scanf("%d", &T);
    while(T--){
        int visit[300][300] = {}, front = 0, rear = 1, cnt = 0, check = 0;
        scanf("%d %d %d %d %d", &I, &queue[0].x, &queue[0].y, &X, &Y);
        visit[queue[0].x][queue[0].y] = 1;
        while(1){
            int i, nx, ny, back = rear;
            while(front < back){
                Pair cur = queue[front++];
                if(cur.x == X && cur.y == Y){
                    check = 1;
                    break;
                }
                for(i = 0; i < 8; i++){
                    nx = cur.x + dx[i];
                    ny = cur.y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < I && ny < I && !visit[nx][ny]){
                        visit[nx][ny] = 1;
                        queue[rear++] = (Pair){nx, ny};
                    }
                }
            }
            if(check)
                break;
            cnt++;
        }
        printf("%d\n", cnt);
    }

    return 0;
}