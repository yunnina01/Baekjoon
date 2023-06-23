#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct{
    double w;
    int u, v;
}Edge;

typedef struct{
    int x, y;
}Coord;

Edge edge[500000];
Coord pos[1001];
int parent[1001], idx;
double res;

int asc(const void *a, const void *b){
    return ((Edge*)a)->w < ((Edge*)b)->w ? -1 : 1;
}

double get_dis(Coord c1, Coord c2){
    double x = c2.x - c1.x, y = c2.y - c1.y;
    return sqrt(x * x + y * y);
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
    int N, M, i, j, x, y;
    scanf("%d %d", &N, &M);
    for(i = 1; i <= N; i++){
        scanf("%d %d", &pos[i].x, &pos[i].y);
        parent[i] = i;
    }
    for(i = 1; i < N; i++){
        for(j = i + 1; j <= N; j++)
            edge[idx++] = (Edge){get_dis(pos[i], pos[j]), i, j};
    }
    qsort(edge, idx, sizeof(Edge), asc);
    while(M--){
        scanf("%d %d", &x, &y);
        x = find(x), y = find(y);
        unions(x, y);
    }
    for(i = 0; i < idx; i++){
        x = find(edge[i].u), y = find(edge[i].v);
        if(x != y){
            unions(x, y);
            res += edge[i].w;
        }
    }
    printf("%.2f\n", res);

    return 0;
}