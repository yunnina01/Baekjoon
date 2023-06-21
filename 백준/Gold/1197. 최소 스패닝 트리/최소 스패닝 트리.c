#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int u, v, w;
}Edge;

Edge edge[100000];
int parent[10001], res;

int asc(const void *a, const void *b){
    return ((Edge*)a)->w - ((Edge*)b)->w;
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
    int V, E, i, x, y;
    scanf("%d %d", &V, &E);
    for(i = 1; i <= V; i++)
        parent[i] = i;
    for(i = 0; i < E; i++)
        scanf("%d %d %d", &edge[i].u, &edge[i].v, &edge[i].w);
    qsort(edge, E, sizeof(Edge), asc);
    for(i = 0; i < E; i++){
        x = find(edge[i].u), y = find(edge[i].v);
        if(x != y){
            unions(x, y);
            res += edge[i].w;
        }
    }
    printf("%d\n", res);

    return 0;
}