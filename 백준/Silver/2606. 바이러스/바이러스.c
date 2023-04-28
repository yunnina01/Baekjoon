#include <stdio.h>

int graph[101][101], visit[101], N, res;

void DFS(int R){
    if(visit[R])
        return;
    visit[R]++, res++;
    for(int i = 1; i <= N; i++){
        if(graph[R][i])
            DFS(i);
    }
}

int main(){
    int M, u, v;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d %d", &u, &v);
        graph[u][v] = graph[v][u] = 1;
    }
    DFS(1);
    printf("%d\n", res - 1);

    return 0;
}