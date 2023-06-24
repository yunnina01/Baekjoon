#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int u, v, w;
}Edge;

Edge bridge[50];
int map[10][10], visit[10][10], parent[7], N, M, cnt, idx, res;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

int asc(const void *a, const void *b){
    return ((Edge*)a)->w - ((Edge*)b)->w;
}

void DFS(int x, int y){
    int i, nx, ny;
    map[x][y] = cnt;
    visit[x][y] = 1;
    for(i = 0; i < 4; i++){
        nx = x + dx[i], ny = y + dy[i];
        if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] && !visit[nx][ny])
            DFS(nx, ny);
    }
}

void move(int x, int y, int num, int dir, int dis){
    if(map[x][y] && dis){
        if(dis > 2)
            bridge[idx++] = (Edge){num, map[x][y], dis - 1};
        return;
    }
    visit[x][y] = 1;
    int nx = x + dx[dir], ny = y + dy[dir];
    if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != num && !visit[nx][ny]){
        move(nx, ny, num, dir, dis + 1);
        visit[nx][ny] = 0;
    }
}

int find(int x){
    if(parent[x] == x)
        return x;
    return parent[x] = find(parent[x]);
}

void unions(int x, int y){
    if(x > y)
        parent[x] = y;
    else
        parent[y] = x;
}

int main(){
    int i, j, k;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++)
            scanf("%d", &map[i][j]);
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            if(map[i][j] && !visit[i][j]){
                cnt++;
                DFS(i, j);
            }
        }
    }
    for(i = 1; i <= cnt; i++)
        parent[i] = i;
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++)
            visit[i][j] = 0;
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            if(map[i][j]){
                for(k = 0; k < 4; k++)
                    move(i, j, map[i][j], k, 0);
            }
        }
    }
    qsort(bridge, idx, sizeof(Edge), asc);
    for(i = 0; i < idx; i++){
        j = find(bridge[i].u), k = find(bridge[i].v);
        if(j != k){
            unions(j, k);
            res += bridge[i].w;
        }
    }
    j = find(1);
    for(i = 2; i <= cnt; i++){
        if(find(i) != j)
            break;
    }
    if(i > cnt)
        printf("%d\n", res);
    else
        puts("-1");

    return 0;
}