#include <stdio.h>
#include <stdlib.h>

int *graph[100001];
int queue[100000], visit[100001], size[100001], front, rear, cnt;

int desc(const void *a, const void *b){
    return *(int*)b - *(int*)a;
}

void BFS(int R){
    int i, cur, next;
    visit[R] = ++cnt;
    queue[rear++] = R;
    while(front != rear){
        cur = queue[front++];
        for(i = 0; i < size[cur]; i++){
            next = graph[cur][i];
            if(!visit[next]){
                visit[next] = ++cnt;
                queue[rear++] = next;
            }
        }
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
        qsort(graph[i], size[i], sizeof(int), desc);
    BFS(R);
    for(i = 1; i <= N; i++)
        printf("%d\n", visit[i]);

    return 0;
}