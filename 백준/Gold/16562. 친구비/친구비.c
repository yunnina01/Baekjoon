#include <stdio.h>

int parent[10001], money[10001], visit[10001];

int find(int x){
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

void unions(int x, int y){
    if(money[x] > money[y])
        parent[x] = y;
    else
        parent[y] = x;
}

int main(){
    int N, M, k, i, v, w, res = 0;
    scanf("%d %d %d", &N, &M, &k);
    for(i = 1; i <= N; i++){
        scanf("%d", &money[i]);
        parent[i] = i;
    }
    while(M--){
        scanf("%d %d", &v, &w);
        unions(find(v), find(w));
    }
    for(i = 1; i <= N; i++){
        v = find(i);
        if(!visit[v]){
            res += money[v];
            visit[v] = 1;
        }
    }
    if(res > k)
        puts("Oh no");
    else
        printf("%d\n", res);

    return 0;
}