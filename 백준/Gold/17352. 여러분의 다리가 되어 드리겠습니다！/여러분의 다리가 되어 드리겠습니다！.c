#include <stdio.h>

int parent[300001];

int find(int x){
    if(x == parent[x])
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
    int N, i, x, y;
    scanf("%d", &N);
    for(i = 1; i <= N; i++)
        parent[i] = i;
    for(i = 2; i < N; i++){
        scanf("%d %d", &x, &y);
        unions(find(x), find(y));
    }
    for(i = 2; i <= N; i++){
        x = find(1);
        if(x != find(i))
            break;
    }
    printf("1 %d\n", i);

    return 0;
}