#include <stdio.h>

int parent[51], truth[50], party[50][50];

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
    int N, M, T, P, i, j, k, check, res;
    scanf("%d %d %d", &N, &M, &T);
    for(i = 1; i <= N; i++)
        parent[i] = i;
    res = M;
    for(i = 0; i < T; i++)
        scanf("%d", &truth[i]);
    for(i = 0; i < M; i++){
        scanf("%d", &P);
        for(j = 0; j < P; j++){
            scanf("%d", &party[i][j]);
            if(!j)
                k = find(party[i][0]);
            else
                unions(k, find(party[i][j]));
        }
    }
    for(i = 0; i < T; i++)
        truth[i] = find(truth[i]);
    for(i = 0; i < M; i++){
        check = 0;
        for(j = 0; party[i][j]; j++){
            for(k = 0; k < T; k++){
                if(find(party[i][j]) == truth[k]){
                    check = 1;
                    break;
                }
            }
            if(check)
                break;
        }
        if(check)
            res--;
    }
    printf("%d\n", res);
    
    return 0;
}