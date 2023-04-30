#include <stdio.h>
#include <stdlib.h>

int graph[25][25], num[320], N, cnt;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

void DFS(int x, int y){
    if(x < 0 || y < 0 || x >= N || y >= N || !graph[x][y])
        return;
    graph[x][y] = 0;
    num[cnt]++;
    for(int i = 0; i < 4; i++)
        DFS(x + dx[i], y + dy[i]);
}

int main(){
    int i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%1d", &graph[i][j]);
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++){
            if(graph[i][j]){
                DFS(i, j);
                cnt++;
            }
        }
    }
    qsort(num, cnt, sizeof(int), asc);
    printf("%d\n", cnt);
    for(i = 0; i < cnt; i++)
        printf("%d\n", num[i]);

    return 0;
}