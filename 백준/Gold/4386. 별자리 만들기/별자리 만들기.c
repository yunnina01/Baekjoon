#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct{
    double w;
    int u, v;
}Edge;

typedef struct{
    double x, y;
}Coord;

Edge edge[5000];
Coord pos[100];
int parent[100], idx;
double res;

int asc(const void *a, const void *b){
    return ((Edge*)a)->w - ((Edge*)b)->w;
}

double get_dis(Coord p1, Coord p2){
    double x = p2.x - p1.x, y = p2.y - p1.y;
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
    int i, j, n, x, y;
    scanf("%d", &n);
    for(i = 1; i < n; i++)
        parent[i] = i;
    for(i = 0; i < n; i++)
        scanf("%lf %lf", &pos[i].x, &pos[i].y);
    for(i = 0; i < n - 1; i++){
        for(j = i + 1; j < n; j++)
            edge[idx++] = (Edge){get_dis(pos[i], pos[j]), i, j};
    }
    qsort(edge, idx, sizeof(Edge), asc);
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