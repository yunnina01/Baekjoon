#include <stdio.h>

int parent[500000], rank[500000], res;

int find(int x){
    if(parent[x] == x)
        return x;
    return parent[x] = find(parent[x]);
}

void unions(int x, int y){
    if(rank[x] < rank[y]){
        int temp = x;
        x = y, y = temp;
    }
    rank[x] += rank[y];
    parent[y] = x;
}

int main(){
    int i, n, m, x, y;
    scanf("%d %d", &n, &m);
    for(i = 0; i < n; i++){
        parent[i] = i;
        rank[i] = 1;
    }
    for(i = 1; i <= m; i++){
        scanf("%d %d", &x, &y);
        x = find(x), y = find(y);
        if(!res && x == y)
            res = i;
        unions(x, y);
    }
    printf("%d\n", res);

    return 0;
}