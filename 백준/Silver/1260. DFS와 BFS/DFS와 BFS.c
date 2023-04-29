#include <stdio.h>

int graph[1001][1001], dfs[1001], bfs[1001], queue[1000], front, rear, N;

void DFS(int V){
    if(dfs[V])
        return;
    dfs[V]++;
    printf("%d ", V);
    for(int i = 1; i <= N; i++){
        if(graph[V][i])
            DFS(i);
    }
}

void BFS(int V){
    bfs[V]++;
    queue[rear++] = V;
    while(front != rear){
        int i, cur = queue[front++];
        printf("%d ", cur);
        for(i = 1; i <= N; i++){
            if(graph[cur][i] && !bfs[i]){
                bfs[i]++;
                queue[rear++] = i;
            }
        }
    }
}

int main(){
    int M, V, u, v;
    scanf("%d %d %d", &N, &M, &V);
    while(M--){
        scanf("%d %d", &u, &v);
        graph[u][v] = graph[v][u] = 1;
    }
    DFS(V);
    puts("");
    BFS(V);
    puts("");

    return 0;
}