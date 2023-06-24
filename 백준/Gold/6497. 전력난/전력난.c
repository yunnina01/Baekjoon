#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int u, v, w;
}Edge;

Edge edge[200000];
int parent[200000];

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
    int i, m, n, x, y, res;
    while(1){
        scanf("%d %d", &m, &n);
        if(!m && !n)
            break;
        for(i = 1; i < m; i++)
            parent[i] = i;
        res = 0;
        for(i = 0; i < n; i++)
            scanf("%d %d %d", &edge[i].u, &edge[i].v, &edge[i].w);
        qsort(edge, n, sizeof(Edge), asc);
        for(i = 0; i < n; i++){
            x = find(edge[i].u), y = find(edge[i].v);
            if(x != y)
                unions(x, y);
            else
                res += edge[i].w;
        }
        printf("%d\n", res);
    }

    return 0;
}