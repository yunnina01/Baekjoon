#include <stdio.h>

typedef struct{
    int x, y, z;
}Coord;

Coord queue[1000000];
int visit[100][100][100], front, rear;
int dx[] = {-1, 0, 1, 0, 0, 0}, dy[] = {0, -1, 0, 1, 0, 0}, dz[] = {0, 0, 0, 0, -1, 1};

int main(){
    int M, N, H, i, j, k, n, nx, ny, nz, cnt = 0, back;
    scanf("%d %d %d", &M, &N, &H);
    for(k = 0; k < H; k++){
        for(i = 0; i < N; i++){
            for(j = 0; j < M; j++){
                scanf("%d", &n);
                if(n)
                    visit[i][j][k] = 1;
                if(n == 1)
                    queue[rear++] = (Coord){i, j, k};
            }
        }
    }
    while(1){
        back = rear;
        while(front < back){
            Coord cur = queue[front++];
            for(i = 0; i < 6; i++){
                nx = cur.x + dx[i], ny = cur.y + dy[i], nz = cur.z + dz[i];
                if(nx >= 0 && ny >= 0 && nz >= 0 && nx < N && ny < M && nz < H && !visit[nx][ny][nz]){
                    visit[nx][ny][nz] = 1;
                    queue[rear++] = (Coord){nx, ny, nz};
                }
            }
        }
        if(back == rear)
            break;
        cnt++;
    }
    for(k = 0; k < H; k++){
        for(i = 0; i < N; i++){
            for(j = 0; j < M; j++){
                if(!visit[i][j][k]){
                    cnt = -1;
                    break;
                }
            }
            if(j != M)
                break;
        }
        if(i != N)
            break;
    }
    printf("%d\n", cnt);

    return 0;
}