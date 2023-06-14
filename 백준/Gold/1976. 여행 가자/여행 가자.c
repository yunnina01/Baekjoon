#include <stdio.h>

int parent[201];

int find(int x){
    if(parent[x] == x)
        return x;
    return parent[x] = find(parent[x]);
}

void unions(int x, int y){
    x = find(x);
    y = find(y);
    if(x != y)
        parent[y] = x;
}

int main(){
    int N, M, i, j, n, t;
    scanf("%d %d", &N, &M);
    for(i = 1; i <= N; i++)
        parent[i] = i;
    for(i = 1; i <= N; i++){
        for(j = 1; j <= N; j++){
            scanf("%d", &n);
            if(n)
                unions(i, j);
        }
    }
    scanf("%d", &n);
    t = find(n);
    while(--M){
        scanf("%d", &n);
        if(t != find(n))
            break;
    }
    if(!M)
        puts("YES");
    else
        puts("NO");

    return 0;
}