#include <stdio.h>
#include <stdlib.h>

int *graph[100001];
int visit[100001], size[100001], cnt;

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

void DFS(int R){
    visit[R] = ++cnt;
    for(int i = 0; i < size[R]; i++){
        if(!visit[graph[R][i]])
            DFS(graph[R][i]);
    }
}

int main(){
    int N, M, R, u, v, i;
    scanf("%d %d %d", &N, &M, &R);
    while(M--){
        scanf("%d %d", &u, &v);
        graph[u] = (int*)realloc(graph[u], sizeof(int) * (size[u] + 1));
        graph[v] = (int*)realloc(graph[v], sizeof(int) * (size[v] + 1));
        graph[u][size[u]++] = v;
        graph[v][size[v]++] = u;
    }
    for(i = 1; i <= N; i++)
        qsort(graph[i], size[i], sizeof(int), asc);
    DFS(R);
    for(i = 1; i <= N; i++)
        printf("%d\n", visit[i]);

    return 0;
}