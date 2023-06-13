#include <stdio.h>

int parent[1000001];

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
    int i, n, m, a, b, op;
    scanf("%d %d", &n, &m);
    for(i = 1; i <= n; i++)
        parent[i] = i;
    while(m--){
        scanf("%d %d %d", &op, &a, &b);
        if(!op)
            unions(a, b);
        else{
            if(find(a) == find(b))
                puts("YES");
            else
                puts("NO");
        }
    }

    return 0;
}